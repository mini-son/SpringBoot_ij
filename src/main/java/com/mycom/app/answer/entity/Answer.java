package com.mycom.app.answer.entity;

import com.mycom.app.question.entity.Question;
import com.mycom.app.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

//Question에 대한 대답(table용)을 관련한 entity
@Entity
@Getter
@Setter
@ToString
public class Answer {

    //field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id(대답pk)
    @Column
    private String content; //content
    @Column
    private LocalDateTime createDate; //create_date 답변등록일
    @Column
    private LocalDateTime modifyDate; //modify_date 답변수정일


    @ManyToOne
    private Question question;

    //한 명의 USER가 여러 개의 답변을 작성할 수 있다.
    @ManyToOne
    //private SiteUser siteUser;
    private SiteUser writer; //user정보(id,username,email,password)
}
