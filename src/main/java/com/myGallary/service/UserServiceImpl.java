package com.myGallary.service;

import com.myGallary.Repository.AccountRepository;
import com.myGallary.Repository.ERole;
import com.myGallary.Repository.RoleRepository;
import com.myGallary.entity.Account;
import com.myGallary.entity.AccountDto;
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

    @Value("${user.code}")
    public String usercode;

    @Value("${admin.code}")
    public String admincode;

    @Override
    public Account getUserByEmail(String email) throws Exception {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account getUserByUsername(String username) throws Exception {
        return accountRepository.findByUsername(username);
    }


    @Override
    public void setUser(Account user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setIsActive(true);

        Role userRole = null;

        if(user.getUsercode().equals(admincode)) {
            userRole = roleRepository.findByRole(ERole.ADMIN.getValue());
        }

        /*
        주솟값 문제 인듯한데
        properties 파일의 데이터는 그 자체가 String type 이기 때문에 문자열 비교할때는
        properties 내부에 "your name" 이런 방식으로 적을 필요가 없다.
         */

        else if(user.getUsercode().equals(usercode)) {
            userRole = roleRepository.findByRole(ERole.MANAGER.getValue());
        }
        else {
            userRole = roleRepository.findByRole(ERole.GUEST.getValue());
        }

        System.out.println(userRole.getRole());

        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));


        accountRepository.save(user);

        // return accountRepository.save(user);
    }
}
