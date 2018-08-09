package com.lam.eshopv2.socials.dao.facebook;

import com.lam.eshopv2.socials.facebook.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by a.lam.tuan on 27. 7. 2018.
 */
@Repository
public interface FacebookUserDao extends JpaRepository<FacebookUser,String>{


/*
    @Autowired
    DataSource dataSource;



    @Autowired
    @PersistenceContext(unitName = Constants.JPA_UNIT_NAME_1)
    private EntityManager entityManager;

   // @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Transactional
    public void save(FacebookUser facebookUser){
       // Session session = this.sessionFactory.getCurrentSession();
       entityManager.persist(facebookUser);        entityManager.flush();

    }*/
}
