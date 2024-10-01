package com.project.universitymarketplace.domain.User.controller;


import com.project.universitymarketplace.domain.User.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.project.universitymarketplace.common.common.CommonURI.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(LOGIN_API)
public class LoginController {

    private LoginService loginService;

}
