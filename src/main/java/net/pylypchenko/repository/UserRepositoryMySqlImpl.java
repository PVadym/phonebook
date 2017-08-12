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
 * A class provides a set of methods with entity {@link User} for the operation in a database,
 * implements {@link UserRepository} in case active profile "profileMysql"
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Repository
@Profile("profileMysql")
public class UserRepositoryMySqlImpl implements UserRepository  {

    /**
     * An instance of {@link EntityManager}
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * The method founds {@link User} in database by username
     *
     * @param username a name of user
     * @return founded user with entered name or
     * NULL in case if user with entered name is not exist in database
     */
    @Override
    public User findByUsername(String username) {
        List<User> list= entityManager.createQuery("select u from User u where u.username= :username")
                .setParameter("username", username).getResultList();
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    /**
     * The method adds a new user into database
     *
     * @param user new user to add
     * @return added user
     */
    @Override
    public User save(User user) {
        return entityManager.merge(user);
    }
}
