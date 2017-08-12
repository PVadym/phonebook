package net.pylypchenko.service;

import net.pylypchenko.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface provides a set of methods for the operation with entity {@link User}
 * Extends {@link UserDetailsService}
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
public interface UserService extends UserDetailsService {

    /**
     * The method founds user by username
     *
     * @param username a name of user
     * @return founded uses with entered name
     */
    User findByUsername(String username);

    /**
     * The method adds a new user
     *
     * @param user new user to add
     * @return added user
     */
    User add(User user);

    /**
     * Method for getting authenticated user
     *
     * @return authenticated user
     */
    User getAuthenticatedUser();
}
