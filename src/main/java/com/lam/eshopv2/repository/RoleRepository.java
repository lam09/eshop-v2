package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}