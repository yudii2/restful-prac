package com.restfulprac.module.account;

import com.restfulprac.module.account.dto.SignUpRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//@SequenceGenerator()
@Entity
@Getter
public class Account {  //create, update는 도메인 객체에서 처리되어야함 ***
    @Id
    @GeneratedValue //알아서 생성하라고 jpa에게 권한 위임
    //@Column(name = "account_id")    //함부로 개발 중간에 추가하지마라
    private Long id;    //Integer보다 BicInt(Long)이 범위가 더 넓음

    @Column(unique = true, nullable = false)
    private String email;

    //@JsonIgnore //api를 만들 때 controller에서 화면으로 객체자체를 전달하는 것은 보안상 위험! 이 어노테이션으로 password는 숨길 수 있다.
    private String password;

    private LocalDateTime joinedAt;

    public static Account createAccount(SignUpRequest request, String encodedPassword) {
        Account account = new Account();
        account.email = request.getEmail();
        account.password = encodedPassword;
        account.joinedAt = LocalDateTime.now();
        return account;
    }

    public void passwordModify(String newPassword) {
        this.password = newPassword;
    }
}
