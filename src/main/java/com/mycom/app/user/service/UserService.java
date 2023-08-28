package com.mycom.app.user.service;

import com.mycom.app.user.entity.SiteUser;
import com.mycom.app.user.reposotory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //회원가입처리
    public SiteUser create(String username,String email,String password){
        //여기에서는 일단(컨트롤러부터는 DTO로 받아서 작업할 예정)
        //SiteUser Entity로 UserRepository와 연동
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setEmail(email);
        //암호는 스프링시큐리티를 이용해서 암호와하여 비번을 저장할 예정
        siteUser.setPassword(password);
        userRepository.save(siteUser);
        return siteUser;
    }
}
