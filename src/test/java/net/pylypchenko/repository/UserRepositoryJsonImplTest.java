package net.pylypchenko.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.pylypchenko.entity.User;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.Valid;
import java.io.File;
import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link UserRepositoryJsonImpl}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryJsonImplTest {

    private UserRepositoryJsonImpl userRepository;

    private User user;

    private Field db;

    public UserRepositoryJsonImplTest() {
        this.userRepository = new UserRepositoryJsonImpl();
        this.user = new User();
        this.user.setId(1);
        this.user.setUsername("test");
        this.user.setPassword("pass");
    }

    @Before
    public void setUp() throws Exception {
        Class v = userRepository.getClass();
        db = v.getDeclaredField("db");
        db.setAccessible(true);

    }

    @After
    public void tearDown() throws Exception {
        FileUtils.deleteDirectory(new File("D:\\jsondbTest\\" + File.separator + "users"));
    }

    @AfterClass
    public static void afterTest() throws Exception {
        FileUtils.deleteDirectory(new File("D:\\jsondbTest\\"));

    }

    @Test
    public void save() throws Exception {

        db.set(userRepository,"D:\\jsondbTest\\");
        userRepository.save(user);
        assertNotNull(userRepository.findByUsername(user.getUsername()));
        assertEquals(user,userRepository.findByUsername(user.getUsername()));
    }

    @Test
    public void findByUsername() throws Exception {
            db.set(userRepository,null);
            assertNull(userRepository.findByUsername(user.getUsername()));

    }

}