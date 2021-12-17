package com.eatsmap.module.account;

import com.restfulprac.module.account.Account;
import com.restfulprac.module.account.AccountRepository;
import com.restfulprac.module.account.dto.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void saveAccountTest() {
        //GIVEN
        SignUpRequest request = new SignUpRequest();
        request.setEmail("wow@naver.com");
        request.setPassword("123123");
        Account account = Account.createAccount(request, "123123");

        //WHEN
        Account savedAccount = accountRepository.save(account);

        //THEN
        assertEquals("wow@naver.com", savedAccount.getEmail()); //예상값과 실존하는 값이 일치하냐
    }
}