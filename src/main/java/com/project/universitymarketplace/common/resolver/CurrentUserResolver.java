package com.project.universitymarketplace.common.resolver;

import com.project.universitymarketplace.common.annotation.CurrentUser;
import com.project.universitymarketplace.domain.User.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 컨트롤러 메서드에 현재 인증된 사용자를 자동으로 주입하는 커스텀 Argument Resolver입니다.
 *
 * <p>이 클래스는 {@link CurrentUser} 애노테이션이 포함된 메서드 파라미터를 탐지하고,
 * {@link LoginService}를 사용하여 현재 인증된 사용자 정보를 반환합니다.</p>
 *
 * <p>사용 예시:</p>
 * <pre>
 * {@code
 * @GetMapping("/profile")
 * public ResponseEntity<User> getUserProfile(@CurrentUser User user) {
 *     return ResponseEntity.ok(user);
 * }
 * }
 * </pre>
 *
 * <p>{@link HandlerMethodArgumentResolver} 인터페이스를 구현하여, 해당 애노테이션이 있는 경우에만
 * 파라미터 바인딩을 수행하도록 처리합니다.</p>
 *
 * @author 전대홍
 */
@Component
@RequiredArgsConstructor
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    private final LoginService loginService;

    /**
     * 메서드 파라미터에 {@link CurrentUser} 애노테이션이 있는지 확인합니다.
     *
     * @param methodParameter 검사할 메서드 파라미터
     * @return {@link CurrentUser} 애노테이션이 있으면 true, 그렇지 않으면 false
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    /**
     * 현재 인증된 사용자 정보를 반환합니다.
     *
     * <p>{@link LoginService}를 사용하여 현재 사용자를 조회하고, 해당 정보를 컨트롤러 메서드에 전달합니다.</p>
     *
     * @param methodParameter      처리할 메서드 파라미터
     * @param modelAndViewContainer 현재 요청에 대한 ModelAndViewContainer
     * @param nativeWebRequest     현재 웹 요청 객체
     * @param webDataBinderFactory 현재 요청의 DataBinderFactory
     * @return 현재 인증된 사용자 객체
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        return loginService.getCurrentUser();
    }

}
