package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public interface PropertyRepository extends JpaRepository<Property,Integer> {

    /*
    @Query("SELECT p FROM property p WHERE LOWER(p.name) = LOWER(:name)")
    public List<Property> findAllByPropertyName(@Param("name") String name);

*/
}
