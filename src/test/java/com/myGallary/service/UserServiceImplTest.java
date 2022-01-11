package com.myGallary.service;

import com.myGallary.Repository.AccountRepository;
import com.myGallary.entity.Account;
import com.myGallary.entity.Role;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private AccountRepository accountRepository;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void create() throws Exception {



      //  userService.setUser(account);

    }

}