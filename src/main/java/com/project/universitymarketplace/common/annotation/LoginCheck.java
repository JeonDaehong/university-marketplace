package com.project.universitymarketplace.common.annotation;

import java.lang.annotation.*;

/**
 * <h1>LoginCheck 애노테이션</h1>
 *
 * 특정 컨트롤러 메서드에 접근 권한을 설정하기 위한 애노테이션입니다.
 *
 * <p>이 애노테이션은 {@link Role}을 통해 접근 권한을 지정하며, AOP를 사용하여 런타임에 권한 체크를 수행합니다.</p>
 *
 * <h2>설정 정보</h2>
 * <ul>
 *     <li><b>Target:</b> 메서드 (Method)</li>
 *     <li><b>Retention:</b> 클래스 (Class) 수준</li>
 * </ul>
 *
 * <p>해당 애노테이션은 메서드 레벨에만 적용되며, 런타임에 {@link org.aspectj.lang.annotation.Aspect}로
 * 권한 체크가 수행됩니다. 이때, 런타임에 프록시 객체가 생성되지만, 클래스 수준으로 어노테이션을 유지해도
 * 런타임에서 정상적으로 권한이 적용되므로 {@code RetentionPolicy.CLASS}로 지정할 수 있습니다.</p>
 *
 * <h2>사용 예시</h2>
 * <pre>
 * {@code
 * @LoginCheck(role = Role.ADMIN)
 * public ResponseEntity<String> adminAccess() {
 *     return ResponseEntity.ok("Hello Admin");
 * }
 * }
 * </pre>
 *
 * @see Role
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
