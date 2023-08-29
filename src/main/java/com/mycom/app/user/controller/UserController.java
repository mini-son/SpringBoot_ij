package com.mycom.app.user.controller;

import com.mycom.app.user.service.UserService;
import com.mycom.app.user.validation.UserCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    private final UserService userService;

    //로그인폼을 보여줘요청
    /*요청주소 /user/login
    요청방식 get*/
    @GetMapping("/login")
    public String login(){
        return "login_form";//templates폴더하위의 login_form.html문서
    }


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
        //비밀번호와 비밀번호확인을 서로 비교하여 불일치하면 singup_form.html문서로 이동
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2","password2InCorrect","비밀번호와 비빌번호확인 일치하지 않습니다.");
            return "signup_form";//templates폴더하위의 singup_form.html문서
        }
        try{
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(),
                    userCreateForm.getPassword1());
        }catch (DataIntegrityViolationException e){
            //여기에서는 username(회원id은 uk, email은 uk)->제약조건에 걸리면 발생
            e.printStackTrace();
            bindingResult.reject("signupFailed","이미 등록된 회원입니다.");
            return "signup_form"; //singup_form.html문서로 이동
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed",e.getMessage());
            return "signup_form"; //singup_form.html문서로 이동
        }

        //3.Model //4.View
        return "redirect:/"; //회원가입성공시 메인화면으로 이동
    }
}
