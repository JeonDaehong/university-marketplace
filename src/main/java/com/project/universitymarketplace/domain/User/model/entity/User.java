package com.project.universitymarketplace.domain.User.model.entity;

import com.project.universitymarketplace.common.annotation.LoginCheck;
import com.project.universitymarketplace.common.common.CommonDateEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity 에 NoArgsConstructor 로 기본 생성자를 생성해주는 이유는
 * JPA에서 런타임 시점에 기본생성자를 통해 클래스를 인스턴스화 하여 값을 동적으로 패밍하기 떄문입니다.
 * 그래서 Entity에서 기본 생성자는 반드시 존재해야합니다.
 * 또한 NoArgsConstructor(access = AccessLevel.PROTECTED) 어노테이션의 뜻은
 * "아무런 매개변수가 없는 생성자를 생성하되 다른 패키지에 소속된 클래스는 접근을 불허한다." 입니다.
 * 즉, 아래와 같은 코드를 생성해 준다는 의미입니다.
 * protected Post() { }
 * 접근 권한을 Protected 로 하는 이유는,
 * public 을 사용할 시 Entity 를 무분별하게 수정할 수 없게 하기 위함과
 * private 사용시 Entity의 Proxy 조회 문제가 발생하기 때문입니다.
 * JPA에서는 Lazy 지연 로딩 방식을 권장하는데 이 때 객체가 Proxy 객체로 존재합니다.
 * 근데 Proxy 객체는 기존 Entity class를 상속 받아 만들어지므로, private 생성자면 상속받아 가져올 수가 없습니다.
 * <br><br>
 * 물론 아래 코드에서는 @ManyToOne(fetch = FetchType.LAZY) 과 같이 Entity 간의 매핑관계가 없어서
 * 지연 로딩 되는 경우가 없기에, Private 으로 설정하여도 무관합니다.
 * 다만, 앞으로 어떻게 될 지 모르기에, 그리고 레퍼런스 자체에서 Protected 를 권장하기에
 * Protected 로 사용하였습니다.
 *
 */
@Entity
@Getter
@ToString
@Table(name = "TB_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends CommonDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_ROLE")
    @Enumerated(value = EnumType.STRING)
    private LoginCheck.Role userRole;

    @Column(name = "EMAIL", unique = true) // 중복을 허용하지 않도록 설정
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "POINT")
    private Long point;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        DEFAULT("정상"),
        STOPPED("정지"),
        DELETED("삭제");

        private final String description;
    }

    @Builder
    public User(LoginCheck.Role userRole, String email, String password, String nickname) {
        this.userRole = userRole;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.point = 0L;
        this.status = Status.DEFAULT;
    }
}
