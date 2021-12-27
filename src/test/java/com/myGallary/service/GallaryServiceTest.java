package com.myGallary.service;

import com.myGallary.Repository.AccountRepository;
import com.myGallary.Repository.GallaryRepository;
import com.myGallary.entity.Account;
import com.myGallary.entity.Gallary;
import com.myGallary.entity.Role;
import com.myGallary.mycontroller.GallaryRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GallaryServiceTest {

    @Autowired
    GallaryRepository gallaryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void create(){




    }

}