package com.project.universitymarketplace.domain.User.controller;


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

import static com.project.universitymarketplace.common.common.CommonURI.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(LOGIN_API)
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;

//    @PostMapping("/login")
//    public ResponseEntity<HttpStatus> login(@RequestBody @Valid LoginRequest request) {
//        // 해당 유저가 존재하는지, 그리고 해당 유저의 Status 가 Default 상태 인지를 확인한다.
//        userService.isValidMember(request);
//        userService.isValidStatus(request.getEmail());
//        loginService.loginMember(userService.findMemberByEmail(request.getEmail()).getId());
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}
