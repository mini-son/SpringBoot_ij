package com.mycom.app.answer.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//(question_detail.html)답변등록폼의 입력값에 대한 유효성검사
/*@NotNull null금지 /""과 " "허용
@NotEmpty  null과 ""금지/ " "허용
@NotBlank  null과 ""과 " "금지*/
@Getter
@Setter
public class AnswerForm {
    //내용
    @NotEmpty(message = "내용은 필수 입력 입니다.")
    private String content;//내용
}
