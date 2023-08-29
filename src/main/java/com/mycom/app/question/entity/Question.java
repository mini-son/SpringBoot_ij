package com.mycom.app.question.entity;

import com.mycom.app.answer.entity.Answer;
import com.mycom.app.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

//question 테이블 관련 데이터를 처리하기 위한 클래스
/* id  int   primary key auto_increment,
 subject  varchar(200),
 content  text,
 create_date  datetime*/
@ToString
@Getter
@Setter
@Entity
public class Question {
    //field
    //@Id : pk
    //@GeneratedValue : 1씩 자동증가
    //GenerationType.IDENTITY : 해당 컬럼만의 독립적인 자동증가번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//글번호. 1씩 증가.pk

    //@Column : 컬럼
    @Column(length = 200)
    private String subject;//제목

    @Column(columnDefinition = "TEXT")
    private String content;//내용

    //필드명이 대소문자 섞인 형태는 _, 컬럼명은 전부 소문자로 처리
    @Column
    private LocalDateTime createDate;//작성일

    /*@OneToMany 속성
    mappedBy는 참조 엔티티의 속성명
    CascadeType.REMOVE : 질문을 삭제하면 그에 딸린 대답목록도 같이 삭제*/
    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    //한 명의 USER가 여러 개의 질문을 작성할 수 있다.
    @ManyToOne
    //private SiteUser siteUser;
    private SiteUser writer; //user정보(id,username,email,password)

    //constructor

    //method
}
