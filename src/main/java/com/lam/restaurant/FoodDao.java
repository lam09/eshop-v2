package com.lam.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a.lam.tuan on 20. 7. 2018.
 */
public interface FoodDao extends JpaRepository<Food,Integer> {
}
