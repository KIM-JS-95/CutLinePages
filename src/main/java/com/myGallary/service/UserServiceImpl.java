package com.myGallary.service;

import com.myGallary.Repository.AccountRepository;
import com.myGallary.Repository.ERole;
import com.myGallary.Repository.RoleRepository;
import com.myGallary.entity.Account;
import com.myGallary.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Account getUserByEmail(String email) throws Exception {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account getUserByUsername(String username) throws Exception {
        return accountRepository.findByUsername(username);
    }


//    @Value("${usercode.code}")
//    public String code;

    @Override
    public Account setUser(Account user) throws Exception {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //< set the active flag
        user.setIsActive(true);
        //< set the user role
        Role userRole = null;


        // username -> usercode 로 변경하여 관리해보자
        // .equals("usercode") 로 변경해도 저장은 admin / user / guest 로 구분되도록
        if(user.getUsercode().equals("admin")) {
            userRole = roleRepository.findByRole(ERole.ADMIN.getValue());
        }
        else if(user.getUsercode().equals( "user" )) {
            userRole = roleRepository.findByRole(ERole.MANAGER.getValue());
        }
        else {
            userRole = roleRepository.findByRole(ERole.GUEST.getValue());
        }

        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        return accountRepository.save(user);
    }
}
