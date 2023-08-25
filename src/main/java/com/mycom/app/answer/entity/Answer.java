package com.mycom.app.answer.entity;

import com.mycom.app.question.entity.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//Question에 대한 대답(table용)을 관련한 entity
@Entity
@Getter
@Setter
public class Answer {

    //field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id(대답pk)
    @Column
    private String content; //content
    @Column
    private LocalDateTime createDate; //create_date

    @ManyToOne
    private Question question;
}
