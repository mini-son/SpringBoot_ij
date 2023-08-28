package com.mycom.app.user.service;

import com.mycom.app.user.entity.SiteUser;
import com.mycom.app.user.reposotory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /*우리는 SecurityConfig.java애서 PasswordEncoder을 Bean으로 등록해두었다.
    참고)BCryptPasswordEncoder클래스는 스프링 시큐리티에서 제공되는 클래스이다.
        이 클래스를 이용해서 패스워드를 암호화해서 처리하도록 한다.
        bcrypt는 패스워드를 저장하는 용도로 설계된 해시 함수로 특정 문자열을 암호화하고,
        체크하는 쪽에서는 암호화된 패스워드가 가능한 패스워드인지만 확인하고
        다시 원문으로 되돌리지는못한다.(교재 p651참고)*/

    //회원가입처리
    public SiteUser create(String username,String email,String password){
        //여기에서는 일단(컨트롤러부터는 DTO로 받아서 작업할 예정)
        //SiteUser Entity로 UserRepository와 연동
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setEmail(email);
        //암호는 스프링시큐리티를 이용해서 암호와하여 비번을 저장
        siteUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(siteUser);
        return siteUser;
    }
}
