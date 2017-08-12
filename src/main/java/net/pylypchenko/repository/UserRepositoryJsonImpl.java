package net.pylypchenko.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pylypchenko.entity.User;
import net.pylypchenko.utils.IdGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

/**
 * A class provides a set of methods with entity {@link User} for the operation in a database,
 * implements {@link UserRepository} in case active profile "profileJson"
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Repository
@Profile("profileJson")
public class UserRepositoryJsonImpl implements UserRepository {

    /**
     * Directory of Json database
     */
    @Value("${json.db}")
    private String db;

    /**
     * Suffix of stored files
     */
    private final String JSON = ".json";

    /**
     * Folder of storage
     */
    private final String USERS_FOLDER = File.separator + "users" + File.separator;

    /**
     * An instance of Logger for logging information
     */
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryJsonImpl.class);


    /**
     * The method founds {@link User} in database by username
     *
     * @param username a name of user
     * @return founded user with entered name or
     * NULL in case if user with entered name is not exist in database
     */
    @Override
    public User findByUsername(String username) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(db + USERS_FOLDER + username + JSON);
        User user;
        try {
            user = mapper.readValue(file, User.class);
        } catch (IOException e) {
            LOGGER.error("Error in DB or User with username =  " + username + " does not exist in db");
            return null;
        }
        return user;
    }

    /**
     * The method adds a new user into database
     *
     * @param user new user to add
     * @return added user
     * @throws RuntimeException in case error during saving user
     */
    @Override
    public User save(User user) {
        user.setId(IdGenerator.getId());
        ObjectMapper mapper = new ObjectMapper();
        File folder = new File(db + USERS_FOLDER);
        File file = new File(db + USERS_FOLDER + user.getUsername() + JSON);
        try {
            if (!file.exists()) {
                if (!folder.exists()) {
                    if (folder.mkdirs() && file.createNewFile()) {
                        mapper.writeValue(file, user);
                    }
                } else
                    mapper.writeValue(file, user);
            }
        } catch (IOException e) {
            LOGGER.error("Error during saving new User with id = " + user.getId());
            throw new RuntimeException(e);
        }
        return user;
    }

}
