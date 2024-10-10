package com.project.universitymarketplace.domain.User.model;

import com.project.universitymarketplace.common.annotation.LoginCheck;
import com.project.universitymarketplace.domain.User.model.entity.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class UserJoinRequest {

    @NotEmpty(message = "이메일은 공란일 수 없습니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotEmpty(message = "패스워드는 공란일 수 없습니다.")
    @Pattern(message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
    private String password;

    @NotEmpty(message = "닉네임은 공란일 수 없습니다.")
    @Size(min = 2, max = 30, message = "닉네임은 최소 2자 이상, 최대 30자 이하여야 합니다.")
    private String nickname;

    @NotNull
    private LoginCheck.Role userRole; // 받을 땐 String 으로 받아서, 넘길 때 Enum 으로 변환

    public static User toEntity(UserJoinRequest request, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .userRole(request.getUserRole())
                .build();
    }

}
