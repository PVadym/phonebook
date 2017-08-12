package net.pylypchenko.repository;

import net.pylypchenko.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Вадим on 08.08.2017.
 */
@Repository
@Profile("profileMysql")
public class UserRepositoryMySqlImpl implements UserRepository  {

    @PersistenceContext
    private EntityManager entityManager;

    @Override

    public User findByUsername(String username) {
        List<User> list= entityManager.createQuery("select u from User u where u.username= :username")
                .setParameter("username", username).getResultList();
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public User save(User user) {
        return entityManager.merge(user);

    }
}
