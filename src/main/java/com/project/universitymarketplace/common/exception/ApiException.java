package com.project.universitymarketplace.common.exception;

import lombok.Getter;
import lombok.ToString;

import java.text.MessageFormat;

@Getter
@ToString
public class ApiException extends RuntimeException {

    private final ExceptionEnum error;
    private final String formattedMessage;

    // 기본 생성자
    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
        this.formattedMessage = e.getMessage();  // 파라미터 없이 기본 메시지 사용
    }

    // 파라미터가 있는 생성자 (예: ID가 있을 때)
    public ApiException(ExceptionEnum e, Object... params) {
        super(formatMessage(e.getMessage(), params));
        this.error = e;
        this.formattedMessage = formatMessage(e.getMessage(), params);
    }

    // 메시지를 안전하게 포맷팅하는 메소드
    private static String formatMessage(String template, Object... params) {
        if (params == null || params.length == 0) {
            return template; // 파라미터가 없으면 템플릿 그대로 사용
        }
        return MessageFormat.format(template, params);
    }

}
