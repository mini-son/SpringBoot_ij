package com.mycom.app.user.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//SiteUser Entity관련
//회원가입폼페이지에서 입력.선택사항에 적용되는 유효성검사 클래스
@Getter
@Setter
public class UserCreateForm {

    @Size(min=3,max=30)
    @NotEmpty(message = "id는 필수입력입니다.")
    private String username;//회원이름.uk

    @NotEmpty(message = "비밀번호는 필수입력입니다.")
    private String password1;//비밀번호

    @NotEmpty(message = "비밀번호는 필수입력입니다.")
    private String password2;//비밀번호 확인용

    @Email
    @NotEmpty(message = "이메일은 필수입력입니다.")
    private String email;//이메일.uk
}
