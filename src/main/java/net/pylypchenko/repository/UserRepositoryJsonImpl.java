package net.pylypchenko.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pylypchenko.entity.User;
import net.pylypchenko.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

/**
 * Created by Вадим on 10.08.2017.
 */
@Repository
@Profile("profileJson")
public class UserRepositoryJsonImpl implements UserRepository {

    @Value("${json.db}")
    private String db;


    private final String JSON = ".json";
    private final String USERS_FOLDER = File.separator + "users" + File.separator;



    @Override
    public User findByUsername(String username) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(db + USERS_FOLDER + username + JSON);
        User user;
        try {
            user = mapper.readValue(file, User.class);
        } catch (IOException e) {
            return null;
        }
        return user;
    }



    @Override
    public User save(User user) {

        user.setId(IdGenerator.getId());
        ObjectMapper mapper = new ObjectMapper();
        File folder = new File(db + USERS_FOLDER);
        File file = new File(db + USERS_FOLDER + user.getUsername()+ JSON);
        try{
            if(!file.exists()){
                if(!folder.exists()) {
                    if (folder.mkdir()) {
                        mapper.writeValue(file, user);
                    }
                } else
                    mapper.writeValue(file, user);
            }

        } catch( IOException e){
            throw new RuntimeException(e);
        }
        return user;
    }


}
