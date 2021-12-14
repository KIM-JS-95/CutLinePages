package com.myGallary.entity;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class AccountTest {

    @Test
    public void create() {
        AccountDto account = AccountDto.builder()
                .usercode("user123")
                .username("k")
                .password("123")
                .confirmPassword("123")
                .email("123")
                .isActive(true)
                .build();

        assertThat(account.getEmail(),is("123"));
    }
}