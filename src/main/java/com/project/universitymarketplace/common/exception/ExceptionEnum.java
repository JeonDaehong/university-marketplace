package com.project.universitymarketplace.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionEnum {

    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "E_0001", "상품을 찾을 수 없습니다. ID: {0}"),
    LOGIN_SECURITY_ERROR(HttpStatus.UNAUTHORIZED, "S_0001", "로그인 정보가 올바르지 않습니다. ID, PW를 다시 입력해주시기 바랍니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
