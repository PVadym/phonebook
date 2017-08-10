package net.pylypchenko.service;

import net.pylypchenko.entity.User;
import net.pylypchenko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by Вадим on 07.08.2017.
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {


    private UserRepository repository;


    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;

    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        if (isBlank(username)) {
            throw new IllegalArgumentException("Incorrect name of User");
        }
        return repository.findByUsername(username);

    }

    @Transactional
    public User add(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("Trying to save 'null'");
        }
        return repository.save(user);
    }


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


