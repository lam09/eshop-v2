package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @org.springframework.data.jpa.repository.Query(value = "SELECT *" +
            "from account where name =:NAME ",nativeQuery = true)
    Account findAccountByName(@Param("NAME") String name);
}