package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Query;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public interface AccountRepository extends JpaRepository<Account, String> {

    Account findAccountByName(String name);
}