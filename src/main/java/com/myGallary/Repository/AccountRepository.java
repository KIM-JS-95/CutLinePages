package com.myGallary.Repository;


import com.myGallary.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    public Account findByEmail(String email);
    public Account findByUsername(String username);
}
