package com.restfulprac.module.account;

import com.restfulprac.module.account.dto.AccountResponse;
import com.restfulprac.module.account.dto.SignUpRequest;
import com.restfulprac.module.account.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true) //save는 예외적으로 transactional을 재선언해줘야한다. readOnly 안됨
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public List<AccountResponse> selectAccounts() {
        List<AccountResponse> response = new ArrayList<>();
        List<Account> data = accountRepository.findAll();

        for (Account account : data) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setEmail(account.getEmail());
            accountResponse.setId(account.getId());
            accountResponse.setJoinedAt(account.getJoinedAt());
            response.add(accountResponse);
        }
        return response;
    }

    @Transactional
    public void saveAccount(SignUpRequest request) {
        Account newAccount = Account.createAccount(request, passwordEncoder.encode(request.getPassword()));
        accountRepository.save(newAccount);
    }

    public AccountResponse findAccountByEmail(String email) {
        Account findAccount = accountRepository.findAccountByEmail(email);
        //예외처리
        if (findAccount != null) {
            AccountResponse response = new AccountResponse();
            response.setId(findAccount.getId());
            response.setEmail(findAccount.getEmail());
            response.setJoinedAt(findAccount.getJoinedAt());
            return response;
        } else {
            throw new UserNotFoundException("사용자가 없습니다.");
        }


    }

}
