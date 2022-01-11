package com.myGallary.entity;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Builder
@Getter
@Data
public class AccountDto {


    private String usercode;


    private String username;


    private String password;

    private String confirmPassword;

    private String email;

    private Boolean isActive;

    private Date regDate;

    private Set<Role> roles;

}
