package com.mycom.app.answer.controller;

import com.mycom.app.answer.service.AnswerService;
import com.mycom.app.answer.validation.AnswerForm;
import com.mycom.app.question.entity.Question;
import com.mycom.app.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.validation.Validator;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    /*요청방식
    요청주소 /answer/add/10 질문번호*/
    //답변등록처리
    @PostMapping("/add/{id}")
    public String addAnswer(@PathVariable("id") Integer id,Model model
                            ,@Valid AnswerForm answerForm, BindingResult bindingResult){
        //1.파라미터받기
        //2.비지니스로직
        //특정글번호의 질문상세 가져오기=>Answer테이블의 fk에 해당하는 질문객체
        /*Answer 엔티티의 속성으로 @ManyToOne private Question question 존재*/
        Question question= questionService.getQuestion(id); //특정글번호의 질문상세조회
        if(bindingResult.hasErrors()){ //유효성검사를 통과하지못하여 에러가 존재하면
            model.addAttribute("question",question);
            return "question_detail"; //question_detail.html로 이동
        }
        answerService.add(question,answerForm.getContent());
        //3.Model
        //4.View
        return String.format("redirect:/question/detail/%d",id); //question_detail
    }
}
