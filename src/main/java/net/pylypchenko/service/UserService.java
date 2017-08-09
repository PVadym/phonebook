package net.pylypchenko.service;

import net.pylypchenko.entity.User;

/**
 * Created by Вадим on 08.08.2017.
 */
public interface UserService {

    User findByUsername(String username);

    User add(User user);
}
