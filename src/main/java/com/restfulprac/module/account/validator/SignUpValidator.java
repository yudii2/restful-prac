package com.restfulprac.module.account.validator;

import com.restfulprac.module.account.AccountRepository;
import com.restfulprac.module.account.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequest request = (SignUpRequest) target;
        if (accountRepository.existsAccountByEmail(request.getEmail())) {
            errors.rejectValue("email", "invalid.email", "이미 존재하는 이메일입니다.");
        }
    }
}
