package com.project.universitymarketplace.common.annotation;

import java.lang.annotation.*;

/**
 * < LoginCheck Annotation >
 * Method 위에 적용할 것이기 때문에 Target 에는 Method 를 적용하고,
 * SPRING AOP 는 Run Time 에 Weaving 을 해주기 때문에 Retention 은 Runtime 으로 지정하는게 맞음.
 * 하지만 사실 프록시 객체는 런타임에 생성하지만, 런타임에 그 프록시를 생성할 타겟 빈을 찾을때 클래스정보(바이트 코드)를 참고하기 때문에,
 * 런타임까지 해당 어노테이션을 유지할 필요는 없음.
 * 그렇기 때문에, Retention 을 클래스까지로해도 똑같이 AOP 적용이 가능
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface LoginCheck {
    Role role();

    enum Role {

          GUEST  // 인증되지 않은 유저
        , USER   // 인증 된 유저
        , ADMIN; // 관리자 권한

        public static Role getRole(String level) {
            return Enum.valueOf(Role.class, level);
        }
    }
}
