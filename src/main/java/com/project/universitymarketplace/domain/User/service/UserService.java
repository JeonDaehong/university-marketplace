package com.project.universitymarketplace.domain.User.service;

import com.project.universitymarketplace.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join( ) {
    }

    /**
     * 읽기 전용 서비스에는 @Transactional(readOnly = true) 를 추가해주었습니다.
     * 해당 어노테이션을 붙이는 이유는 오직 읽기 작업(SELECT)만 수행한다는 걸 명시적으로 알리기 위함도 있고,
     * JPA 의 더티체킹 변경감지가 일어나지 않아 메모리를 절약할 수 있으며,
     * 트랜잭션 ID를 부여하지 않으므로 오버헤드가 일어나지 않아 성능상 이점이 생기기 때문입니다.
     */
    @Transactional(readOnly = true)
    public void existsByEmail(String email) {
    }

}
