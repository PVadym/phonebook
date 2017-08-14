package net.pylypchenko.service;

import net.pylypchenko.entity.User;
import net.pylypchenko.enums.UserRole;
import net.pylypchenko.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


/**
 * Class for testing {@link UserServiceImpl}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFullName("fullname");
    }

    @Test
    public void findByUsername() throws Exception {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        assertEquals(userService.findByUsername(user.getUsername()), user);
        when(userRepository.findByUsername("null")).thenReturn(null);
        assertNull(userService.findByUsername("null"));

        verify(userRepository,times(2)).findByUsername(anyString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUsernameBlank() throws Exception {
        userService.findByUsername(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void add() throws Exception {
        //prepare
        when(userRepository.save(user)).thenReturn(user);
        //testing
        User userToTest = userService.add(user);
        //validate
        verify(userRepository).save(user);
        assertEquals(user,userToTest);
        userService.add(null);

    }

    @Test
    public void getAuthenticatedUser() throws Exception {
        securityContextSetter();
        assertEquals(user, userService.getAuthenticatedUser());
        assertEquals(
                new User(), userService.getAuthenticatedUser()
        );

    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername() throws Exception {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        assertEquals(user, userService.loadUserByUsername(user.getUsername()));
        when(userRepository.findByUsername("null")).thenReturn(null);
        userService.loadUserByUsername("null");
    }

    private void securityContextSetter() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user).thenReturn(new String());
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

}