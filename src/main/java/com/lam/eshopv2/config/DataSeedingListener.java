package com.lam.eshopv2.config;

import com.lam.eshopv2.entity.Account;
import com.lam.eshopv2.entity.PaymentMethod;
import com.lam.eshopv2.entity.Role;
import com.lam.eshopv2.entity.ShippingMethod;
import com.lam.eshopv2.repository.AccountRepository;
import com.lam.eshopv2.repository.PaymentMethodRepository;
import com.lam.eshopv2.repository.RoleRepository;
import com.lam.eshopv2.repository.ShippingMethodRepository;
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

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
/*
        PaymentMethod paymentMethod1=new PaymentMethod();
        paymentMethod1.setName("ON DELIVERY");
        paymentMethod1.setFee(1.00);
        PaymentMethod paymentMethod2=new PaymentMethod();
        paymentMethod2.setName("BANK TRANSFER");
        paymentMethod2.setFee(0.00);
        PaymentMethod paymentMethod3=new PaymentMethod();
        paymentMethod3.setName("CARD");
        paymentMethod3.setFee(1.00);

        paymentMethodRepository.save(paymentMethod1);
        paymentMethodRepository.save(paymentMethod2);
        paymentMethodRepository.save(paymentMethod3);

        ShippingMethod shippingMethod1=new ShippingMethod();
        shippingMethod1.setName("POSTA");
        shippingMethod1.setFee(2.00);
        ShippingMethod shippingMethod2=new ShippingMethod();
        shippingMethod2.setName("OSOBNE");
        shippingMethod2.setFee(0.00);
        ShippingMethod shippingMethod3=new ShippingMethod();
        shippingMethod3.setName("DHL");
        shippingMethod3.setFee(2.00);

        shippingMethodRepository.save(shippingMethod1);
        shippingMethodRepository.save(shippingMethod2);
        shippingMethodRepository.save(shippingMethod3);
*/

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