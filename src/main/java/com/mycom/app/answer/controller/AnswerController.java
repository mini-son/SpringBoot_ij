package com.mycom.app.answer.controller;

import com.mycom.app.answer.service.AnswerService;
import com.mycom.app.answer.validation.AnswerForm;
import com.mycom.app.question.entity.Question;
import com.mycom.app.question.service.QuestionService;
import com.mycom.app.user.entity.SiteUser;
import com.mycom.app.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.validation.Validator;
import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    /*요청방식
    요청주소 /answer/add/10 질문번호*/
    //답변등록처리
    @PreAuthorize("isAuthenticated()") //로그인인증=>로그인이 필요한 기능
    @PostMapping("/add/{id}")
    //유효성검사관련
    //스프링시큐리티가 제공하는 객체. 사용자정보. Principal
    public String addAnswer(@PathVariable("id") Integer id, Model model,
                            @Valid AnswerForm answerForm, BindingResult bindingResult,
                            Principal principal){
        //1.파라미터받기
        //2.비지니스로직
        //특정글번호의 질문상세 가져오기=>Answer테이블의 fk에 해당하는 질문객체
        /*Answer 엔티티의 속성으로 @ManyToOne private Question question 존재*/
        Question question= questionService.getQuestion(id); //특정글번호의 질문상세조회
        if(bindingResult.hasErrors()){ //유효성검사를 통과하지못하여 에러가 존재하면
            model.addAttribute("question",question);
            return "question_detail"; //question_detail.html로 이동
        }
        SiteUser siteUser = userService.getUser(principal.getName()); //user정보 가져오기

        answerService.add(question,answerForm.getContent(),siteUser);
        //3.Model
        //4.View
        return String.format("redirect:/question/detail/%d",id); //question_detail
    }
}
