package com.restfulprac.module.account.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountResponse {

    private Long id;

    private String email;

    private LocalDateTime joinedAt;
}
