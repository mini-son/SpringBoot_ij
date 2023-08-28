package com.mycom.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //스프링의 환경설정 파일임을 공지
@EnableWebSecurity //모든 요청URL이 스프링 시큐리티의 제어를 받도록 만든다
//requestMatchers(new AntPathRequestMatcher("/**")) => 로그인하지않아도 모든 페이지에 접근할 수 있다.
public class SecurityConfig {

    //SecurityFilterChain 클래스 생성
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
             .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
        .csrf((csrf) -> csrf
             .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
        .headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                 XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        ;
        return http.build();
    }

    @Bean //passwordEncoder 생성
    PasswordEncoder passwordEncoder(){
        //BCryptPasswordEncoder클래스는 스프링 시큐리티에서 제공되는 클래스이다.
        /*이 클래스를 이용해서 패스워드를 암호화해서 처리하도록 한다.
        bcrypt는 패스워드를 저장하는 용도로 설계된 해시 함수로 특정 문자열을 암호화하고,
        체크하는 쪽에서는 암호화된 패스워드가 가능한 패스워드인지만 확인하고
        다시 원문으로 되돌리지는못한다.(교재 p651참고)*/
        return new BCryptPasswordEncoder();
    }
}
