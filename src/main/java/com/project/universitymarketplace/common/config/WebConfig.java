package com.project.universitymarketplace.common.config;

import com.project.universitymarketplace.common.resolver.CurrentUserResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Spring MVC 설정을 구성하는 웹 설정 클래스입니다.
 * 이 클래스는 컨트롤러 메서드에 대한 인자 리졸버를 커스터마이징하기 위해 사용됩니다.
 * <p>이 클래스의 주요 기능은 {@link CurrentUserResolver}와 같은 사용자 정의 인자 리졸버를
 * 등록하여 컨트롤러 메서드에서 인자를 자동으로 주입할 수 있도록 설정하는 것입니다.
 * </p>
 * <p>해당 클래스는 {@code WebMvcConfigurer} 인터페이스를 구현하여 MVC 설정을 추가로
 * 커스터마이징할 수 있도록 지원합니다.</p>
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CurrentUserResolver currentUserResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserResolver);
    }

}
