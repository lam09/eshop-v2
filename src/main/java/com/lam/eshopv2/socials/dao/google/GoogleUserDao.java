package com.lam.eshopv2.socials.dao.google;

import com.lam.eshopv2.socials.google.Google_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by a.lam.tuan on 27. 7. 2018.
 */
@Repository
public interface GoogleUserDao extends JpaRepository<Google_user,String> {
/*
    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_2)
    private EntityManager entityManager;

   // @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Transactional
    @Qualifier("transactionManager2")
    public void save(Google_user googlePojo){
        entityManager.persist(googlePojo);
        entityManager.flush();
    }*/
}
