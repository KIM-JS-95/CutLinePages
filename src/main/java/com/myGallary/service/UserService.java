package com.myGallary.service;


import com.myGallary.entity.Account;

public interface UserService {

    public Account getUserByEmail(String email) throws Exception;
    public Account getUserByUsername(String username) throws Exception;
    public Account setUser(Account user) throws Exception;

}
