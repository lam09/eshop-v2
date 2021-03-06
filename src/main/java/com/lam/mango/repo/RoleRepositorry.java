package com.lam.mango.repo;

import com.mango.web.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositorry  extends JpaRepository<Role,Integer> {
    Role findRoleByName(String name);
}
