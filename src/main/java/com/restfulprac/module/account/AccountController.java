package com.restfulprac.module.account;

import com.restfulprac.module.account.dto.AccountResponse;
import com.restfulprac.module.account.dto.SignUpRequest;
import com.restfulprac.module.account.validator.SignUpValidator;
import com.restfulprac.infra.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final SignUpValidator signUpValidator;

    @InitBinder(value = "signUpRequest")    //signUpRequest가 호출될 때 initBinder가 동작하면서 validator를 거침
    public void signUpInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpValidator);
    }

    @GetMapping(path = "/accounts")
    public ResponseEntity selectAllAccounts() {
        List<AccountResponse> data = accountService.selectAccounts();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.createResponse(HttpStatus.OK.toString(), true, "1.0", data));
    }

    @PostMapping(path = "/accounts")
    public ResponseEntity createAccount(@RequestBody @Valid SignUpRequest request) { //Valid 어노테이션을 거쳐야만 Account에 걸어둔 validating 거침
        accountService.saveAccount(request);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping(path = "/accounts/{email}")
    public ResponseEntity findByEmail(@PathVariable String email) {
        AccountResponse data = accountService.findAccountByEmail(email);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.createResponse(HttpStatus.OK.toString(), true, "1.0", data));
    }

}
