package com.project.universitymarketplace.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import static com.project.universitymarketplace.common.exception.ExceptionEnum.*;

/**
 * <h1>글로벌 예외 처리기 (GlobalExceptionHandler)</h1>
 *
 * <p>애플리케이션 전반에 발생하는 예외를 처리하고, 공통된 에러 응답 형식을 반환하기 위한 글로벌 예외 처리 클래스입니다.</p>
 *
 * <p>Spring의 {@link RestControllerAdvice}를 사용하여 모든 컨트롤러에 대한 예외를 감지하고,
 * 발생한 예외 유형에 따라 적절한 HTTP 상태 코드와 예외 메시지를 담은 응답을 생성합니다.</p>
 *
 * <h2>지원하는 예외 유형</h2>
 * <ul>
 *     <li>{@link ApiException} - 사용자 정의 API 예외</li>
 *     <li>{@link HttpClientErrorException} - 외부 API 호출 시 발생하는 HTTP 클라이언트 오류</li>
 * </ul>
 *
 * <h2>응답 형식</h2>
 * <p>예외가 발생할 경우, {@link ExceptionDto} 형식으로 에러 코드를 포함한 응답을 반환합니다.</p>
 * <pre>
 * {@code
 * {
 *     "errorCode": "E_0001",
 *     "errorMessage": "상품을 찾을 수 없습니다."
 * }
 * }
 * </pre>
 *
 * @see RestControllerAdvice
 * @see ExceptionHandler
 * @see ResponseEntity
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 사용자 정의 예외 {@link ApiException} 처리 메서드.
     *
     * <p>애플리케이션 내에서 발생한 {@link ApiException}을 처리하며, 발생한 예외의 상태 코드와
     * 메시지를 포함한 {@link ExceptionDto}를 응답으로 반환합니다.</p>
     *
     * <h3>처리 방식</h3>
     * <ul>
     *     <li>예외 발생 시, 로그에 에러 메시지와 예외 스택 트레이스를 기록합니다.</li>
     *     <li>{@link ApiException#getFormattedMessage()}를 이용하여 최종 오류 메시지를 추출하고, 이를 응답에 포함시킵니다.</li>
     *     <li>응답의 상태 코드는 예외에 정의된 HTTP 상태 코드를 따릅니다.</li>
     * </ul>
     *
     * @param e 발생한 {@link ApiException} 객체
     * @return {@link ResponseEntity} 형태의 예외 응답, 예외 코드와 메시지를 포함한 {@link ExceptionDto} 반환
     */
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ExceptionDto> exceptionHandler(final ApiException e) {
        log.error(">>> Exception Log: {} <<<", e.getFormattedMessage(), e); // formattedMessage 사용
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ExceptionDto.builder()
                        .errorCode(e.getError().getCode())
                        .errorMessage(e.getFormattedMessage()) // 최종 메시지를 사용
                        .build());
    }


    /**
     * {@link HttpClientErrorException} 처리 메서드.
     *
     * <p>외부 API 호출 중 발생한 {@link HttpClientErrorException} 예외를 처리하며,
     * 로그인 관련 보안 오류와 같은 클라이언트 오류를 처리하는 데 사용됩니다.</p>
     *
     * <h3>처리 방식</h3>
     * <ul>
     *     <li>예외 발생 시, 로그에 에러 메시지와 예외 스택 트레이스를 기록합니다.</li>
     *     <li>기본적으로 로그인 보안 관련 에러 코드를 사용하여 응답을 생성합니다.</li>
     * </ul>
     *
     * @param e 발생한 {@link HttpClientErrorException} 객체
     * @return {@link ResponseEntity} 형태의 예외 응답, 로그인 보안 관련 에러 코드와 메시지를 포함한 {@link ExceptionDto} 반환
     */
    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<ExceptionDto> exceptionHandler(final HttpClientErrorException e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        return ResponseEntity
                .status(LOGIN_SECURITY_ERROR.getStatus())
                .body(ExceptionDto.builder()
                        .errorCode(LOGIN_SECURITY_ERROR.getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }
}
