package net.pylypchenko.repository;


import net.pylypchenko.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link UserRepositoryMySqlImpl}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryMySqlImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private UserRepositoryMySqlImpl userRepository;

    private User user;
    private List<User> list;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        list = new ArrayList<>();
        list.add(user);

    }

    @Test
    public void findByUsername() throws Exception {
        //prepare
        when(entityManager.createQuery("select u from User u where u.username= :username")).thenReturn(query);
        when(query.setParameter("username", user.getUsername())).thenReturn(query);
        when(query.getResultList()).thenReturn(new ArrayList()).thenReturn(list);

        //testing
        User user1 = userRepository.findByUsername(user.getUsername());
        User user2 = userRepository.findByUsername(user.getUsername());

        //validate
        verify(entityManager, times(2)).createQuery("select u from User u where u.username= :username");
        assertNull(user1);
        assertEquals(user2.getUsername(),"username");
    }

    @Test
    public void save() throws Exception {
        //prepare
        User savedUser = new User();
        savedUser.setId(1);
        when(entityManager.merge(user)).thenReturn(savedUser);

        //testing
        User userToTest = userRepository.save(user);

        //validate
        verify(entityManager).merge(user);
        assertEquals(savedUser,userToTest);
    }

}