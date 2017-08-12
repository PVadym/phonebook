package net.pylypchenko.repository;

import net.pylypchenko.entity.User;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface provides a set of methods with entity {@link User}
 * for the operation in a database.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@NoRepositoryBean
public interface UserRepository {

    /**
     * The method founds user in database by username
     *
     * @param username a name of user
     * @return founded uses with entered name
     */
    User findByUsername(String username);

    /**
     * The method adds a new user into database
     *
     * @param user new user to add
     * @return added user
     */
    User save(User user);
}
