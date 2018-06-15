package com.lam.eshopv2.services;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */


import com.lam.eshopv2.entity.Account;
import com.lam.eshopv2.entity.Role;
import com.lam.eshopv2.repository.AccountRepository;
import com.lam.eshopv2.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.print("user name is "+name);
        Account user = accountRepository.findAccountByName(name);
        System.out.println("Account= " + user.getName());

        if (user == null) {
            throw new UsernameNotFoundException("User " //
                    + name + " was not found in the database");
        }

        // EMPLOYEE,MANAGER,..
        if(user.getRoles()==null) System.out.print("no role");
        Set<Role> roles = user.getRoles();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        for(Role role: roles)
        {
            // ROLE_EMPLOYEE, ROLE_MANAGER
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            grantList.add(authority);
        }
        boolean enabled = user.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(user.getName(), //
                user.getEncrytedPassword(), enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);

        return userDetails;
    }

/*

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }

*/
}