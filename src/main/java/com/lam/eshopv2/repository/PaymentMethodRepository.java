package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a.lam.tuan on 29. 6. 2018.
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Integer> {

    PaymentMethod findPaymentMethodById(Integer id);
    PaymentMethod findByName(String name);

    PaymentMethod findPaymentMethodByName(String paymentMethod);
}
