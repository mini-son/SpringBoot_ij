package com.mycom.app.question.service;

import com.mycom.app.exception.DataNotFoundException;
import com.mycom.app.question.entity.Question;
import com.mycom.app.question.repository.QuestionRepository;
import com.mycom.app.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
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

    //질문목록조회
    public List<Question> getList(){
        List<Question> questionList = questionRepository.findAll();
        System.out.println(questionList.size());
        return questionList;
    }
}
