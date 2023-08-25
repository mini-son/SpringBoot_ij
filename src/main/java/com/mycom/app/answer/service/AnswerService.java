package com.mycom.app.answer.service;

import com.mycom.app.answer.entity.Answer;
import com.mycom.app.answer.repository.AnswerRepository;
import com.mycom.app.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    //답변등록처리
    public void add(Question question,String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question); //fk격에 해당하는 질문 객체
        answerRepository.save(answer);
    }

}
