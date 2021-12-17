package com.restfulprac.module.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByEmail(String email);

    boolean existsAccountByEmail(String email);
}
