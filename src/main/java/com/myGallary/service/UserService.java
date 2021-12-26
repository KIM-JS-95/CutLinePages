package com.myGallary.service;


import com.myGallary.entity.Account;
import com.myGallary.entity.AccountDto;

public interface UserService {

    public Account getUserByEmail(String email) throws Exception;
    public Account getUserByUsername(String username) throws Exception;
    void setUser(Account user) throws Exception;

}
