package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}
