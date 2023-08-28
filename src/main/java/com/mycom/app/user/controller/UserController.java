package com.mycom.app.user.controller;

import com.mycom.app.user.validation.UserCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    //private final UserService userService;

    //회원가입폼을 보여줘요청
    /*요청주소 /user/signup
    요청방식 get*/
    @GetMapping("/signup")
    public String singup(UserCreateForm userCreateForm){
        return "signup_form"; //templates폴더하위의 singup_form.html문서
    }

    //회원가입 처리 요청
    /*요청주소 /user/signup
    요청방식 post*/
    @PostMapping("/signup")
    public String singup(@Valid UserCreateForm userCreateForm,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){ //에러가 존재하면
            return "signup_form";//templates폴더하위의 singup_form.html문서
        }
        //2.비지니스로직처리
        //3.Model //4.View
        return "redirect:/signup_form"; //회원가입성공시 메인화면으로 이동
    }
}
