package net.pylypchenko.repository;

import net.pylypchenko.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Вадим on 07.08.2017.
 */
@NoRepositoryBean
public interface UserRepository {


    User findByUsername(String username);

    User save(User user);
}
