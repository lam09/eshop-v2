package com.lam.eshopv2.config;

import com.lam.eshopv2.entity.Account;
import com.lam.eshopv2.entity.Role;
import com.lam.eshopv2.repository.AccountRepository;
import com.lam.eshopv2.repository.RoleRepository;
import com.lam.eshopv2.utils.Eshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
     /*   // Roles
        if (roleRepository.findByName(Eshop.ADMIN_ROLE) == null) {
            roleRepository.save(new Role(Eshop.ADMIN_ROLE));
        }

        if (roleRepository.findByName(Eshop.EMPLOYEE_ROLE) == null) {
            roleRepository.save(new Role(Eshop.EMPLOYEE_ROLE));
        }

        // Admin account
        if (userRepository.findAccountByName("admin") == null) {
            Account admin = new Account();
            admin.setName("admin");
            admin.setActive(true);
            admin.setEncrytedPassword(passwordEncoder.encode("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(Eshop.ADMIN_ROLE));
            roles.add(roleRepository.findByName(Eshop.EMPLOYEE_ROLE));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        if (userRepository.findAccountByName("EMPLOYEE") == null) {
            Account user = new Account();
            user.setName("EMPLOYEE");
            user.setActive(true);
            user.setEncrytedPassword(passwordEncoder.encode("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(Eshop.EMPLOYEE_ROLE));
            user.setRoles(roles);
            userRepository.save(user);
        }*/
    }

}