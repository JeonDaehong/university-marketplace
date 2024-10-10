package com.project.universitymarketplace.domain.User.controller;

import com.project.universitymarketplace.domain.User.model.UserJoinRequest;
import com.project.universitymarketplace.domain.User.service.LoginService;
import com.project.universitymarketplace.domain.User.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.universitymarketplace.common.common.CommonURI.LOGIN_API;
import static com.project.universitymarketplace.common.common.CommonURI.USER_API;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(USER_API)
public class UserController {

    private final UserService userService;

    /**
     * RequestParam 과 RequestBody 의 차이
     * RequestParam 은 기본 자료형이나 문자열 등의 단일 값 파라미터를 받을 때 사용하고,
     * RequestBody 는 복잡한 객체나 데이터를 Java Object 형식으로 변환하여 보낼 때 사용합니다.
     */
    @PostMapping("/join")
    public ResponseEntity<HttpStatus> join(@RequestBody @Valid UserJoinRequest userJoinRequest) {
        userService.existsByEmail(userJoinRequest.getEmail());
        userService.join(userJoinRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
