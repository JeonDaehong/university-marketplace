package com.project.universitymarketplace.domain.User.service;

import com.project.universitymarketplace.domain.User.model.entity.User;

/**
 * 로그인 하는 로직은 여러 Controller 에서 사용할 수 있기 때문에 따로 Service 로 구현하였습니다.
 * 현재는 Session 방식이지만, 추후 토큰 등 여러 형태로 구현이 가능하기 때문에
 * Interface 로 만들어 컨트롤러가 로그인 서비스를 간접적으로 의존하도록 하였습니다.
 * 컨트롤러는 LoginService 가 Session 으로 구현하였든, 토큰으로 구현하였든 알 필요가 없습니다.
 */
public interface LoginService {

    User getCurrentUser();

}
