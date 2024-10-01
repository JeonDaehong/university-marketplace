package com.project.universitymarketplace.domain.User.service;

import com.project.universitymarketplace.domain.User.model.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
  *  레디스의 키값으로 저장되는 타입입니다.
 */
@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final HttpSession session;
    public static final String User_ID = "__UserId__";

    @Override
    public User getCurrentUser() {
        return null; // Todo. Get User Logic Develop
    }
}
