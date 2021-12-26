package com.myGallary.Repository;


import com.myGallary.entity.Account;
import com.myGallary.entity.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByEmail(String email);
    public Account findByUsername(String username);
}
