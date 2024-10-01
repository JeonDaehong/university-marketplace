package com.project.universitymarketplace.common.aop;

import com.project.universitymarketplace.common.annotation.LoginCheck;
import com.project.universitymarketplace.domain.User.model.entity.User;
import com.project.universitymarketplace.domain.User.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final LoginService loginService;

    @Before("@annotation(com.project.universitymarketplace.common.annotation.LoginCheck) && @annotation(target)")
    public void loginCheck(LoginCheck target) throws HttpClientErrorException {

        User currentUser = this.getCurrentUser();

        switch (target.role()) {
            case GUEST -> checkUserRole(currentUser, LoginCheck.Role.GUEST);
            case USER -> checkUserRole(currentUser, LoginCheck.Role.USER);
            case ADMIN -> checkUserRole(currentUser, LoginCheck.Role.ADMIN);
        }
    }

    private User getCurrentUser() throws HttpClientErrorException {
        User user = loginService.getCurrentUser();
        if (user == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
        return user;
    }

    private void checkUserRole(User currentMember, LoginCheck.Role requiredRole) {
        if (!(currentMember.getUserRole() == requiredRole)) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

}
