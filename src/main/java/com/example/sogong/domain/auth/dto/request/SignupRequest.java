package com.example.sogong.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record SignupRequest(
        @NotBlank
        @Size(min = 2, max = 20, message = "닉네임은 2자리 이상, 20자리 이하여야 합니다")
        String nickname,

        @NotBlank
        @Email(message = "입력한 이메일 주소에 @ 기호와 도메인 명이 필요합니다")
        @Size(max = 50, message = "이메일 주소는 50자리 이하여야 합니다")
        String email,

        @NotBlank
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 길이, 영문 대소문자, 숫자, 특수문자를 모두 포함해서 사용해주세요")
        String password
) {
}
