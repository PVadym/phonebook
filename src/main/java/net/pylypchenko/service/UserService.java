package net.pylypchenko.service;

import net.pylypchenko.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Вадим on 08.08.2017.
 */
public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User add(User user);
}
