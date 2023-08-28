package com.mycom.app;

import com.mycom.app.question.entity.Question;
import com.mycom.app.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private QuestionRepository questionRepository;

    //요청주소 http://localhost:8090/
    //메인화면이 보여줘야한다.
    //요청주소 http://localhost:8090/question/list
    //일단 여기에서는 질의목록페이지(question_list.html)를 출력
    @GetMapping("/")
    //@ResponseBody
    public String index(){
        return "index"; //templates폴더하위 index.html문서
        //return "redirect:/question/list"; //templates폴더하위 question_list문서
    }
}
