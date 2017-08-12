package net.pylypchenko.service;

import net.pylypchenko.entity.User;
import net.pylypchenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * The class provides a set of methods for operations with {@link User},
 * implementation of {@link UserService}
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * An instance of {@link UserRepository}
     */
    private UserRepository repository;

    /**
     * Constructor
     *
     * @param repository An instance of class that implements {@link UserRepository},
     *                   interface for working with {@link User}
     */
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * The method founds {@link User} in database by username
     *
     * @param username a name of user
     * @return founded user with entered name
     * @throws IllegalArgumentException in case if user`s name is NULL or whitespaces
     */
    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        if (isBlank(username)) {
            throw new IllegalArgumentException("Incorrect name of User");
        }
        return repository.findByUsername(username);
    }

    /**
     * The method adds a new user
     *
     * @param user new user to add
     * @return added user
     * @throws IllegalArgumentException in case if user is NULL
     */
    @Override
    @Transactional
    public User add(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("Trying to save 'null'");
        }
        return repository.save(user);
    }

    /**
     * Method to analyze authenticated user
     *
     * @return boolean value. If user is authenticated, the method returns true, and return false otherwise.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            user = new User();
        }
        return user;
    }

    /**
     * The method load user from database by user`s name
     *
     * @param username a user`s name
     * @return user with entered name
     * @throws UsernameNotFoundException in case if user with entered name is not exist in database
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name " + username + " is not exist in database.");
        }
        return user;
    }
}


