package com.restfulprac.module.account.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {    //사용자로부터 받아야할 값

    //@NotNull    //null만 아니면됨. 공백 허용
    //@NotBlank   //공백 안됨.
    @NotEmpty     //가장 hard한 방법. 공백, null 안됨
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 반드시 입력해주세요.")
    private String password;
}
