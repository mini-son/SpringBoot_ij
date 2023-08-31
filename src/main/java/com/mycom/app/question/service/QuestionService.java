package com.mycom.app.question.service;

import com.mycom.app.exception.DataNotFoundException;
import com.mycom.app.question.entity.Question;
import com.mycom.app.question.repository.QuestionRepository;
import com.mycom.app.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    //삭제처리
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    //질문수정처리
    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        questionRepository.save(question);
    }

    //질문등록처리
    //SiteUser siteUser : 질문작성자의 정보
    public void add(String subject, String content, SiteUser siteUser){
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setWriter(siteUser);
        questionRepository.save(question);
    }

    //질문상세조회
    public Question getQuestion(Integer id){
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("Question not Found");
        }
    }

    //페이징기능이 있는 질문목록조회
    //파라미터 int page- 보고싶은 페이지번호
    public Page<Question> getList(int page){
        //질문목록조회 : findAll()
        //페이징기능이 있는 질문목록조회-Page<Question> findAll(Pageable pageable);
        /*파라미터 int page- 보고싶은 페이지번호
          int size -  1page당 출력할 게시물수
          Sort sort - 정렬*/
        List<Sort.Order> sorts = new ArrayList();
        //Question entity의 createDate속성을 내림차순정렬
        //정렬조건을 추가하고 싶다면 sorts.add()한다.
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
        return questionRepository.findAll(pageable);
    }


}
