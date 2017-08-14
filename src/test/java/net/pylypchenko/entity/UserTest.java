package net.pylypchenko.entity;

import net.pylypchenko.enums.UserRole;
import net.pylypchenko.utils.IdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class for testing {@link User}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */

@RunWith(SpringRunner.class)
public class UserTest {

    private User testUser1;
    private User testUser2;
    private User testUser3;

    @Before
    public void setUp() throws Exception {
        testUser1 = new User();
        testUser1.setUsername("username");
        testUser1.setFullName("fullname");
        testUser1.setPassword("pass");

        testUser2 = new User();
        testUser2.setUsername("username");
        testUser2.setFullName("fullname");
        testUser2.setPassword("pass");

        testUser3 = new User();
        testUser3.setUsername("username");
        testUser3.setFullName("fullname");
        testUser3.setPassword("pass");
    }

    @Test
    public void isAccountNonExpired() throws Exception {

        assertTrue(testUser1.isAccountNonExpired());
        testUser1.setLocked(true);
        assertFalse(testUser1.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked() throws Exception {

        assertTrue(testUser1.isAccountNonLocked());
        testUser1.setLocked(true);
        assertFalse(testUser1.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired() throws Exception {

        assertTrue(testUser1.isCredentialsNonExpired());
        testUser1.setLocked(true);
        assertFalse(testUser1.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled() throws Exception {

        assertTrue(testUser1.isEnabled());
        testUser1.setLocked(true);
        assertFalse(testUser1.isEnabled());
    }

    @Test
    public void getAuthorities() throws Exception {
        testUser1.setRole(UserRole.USER);
        Collection<GrantedAuthority> grantedAuthorities = testUser1.getAuthorities();
        assertNotNull(grantedAuthorities);
        assertTrue(grantedAuthorities.size() == 1);
    }

    @Test
    public void getAndSetId() throws Exception {
        assertEquals(testUser1.getId(), 0);
        testUser1.setId(IdGenerator.getId());
        assertFalse(testUser1.getId() == 0);
    }

    @Test
    public void getAndSetUsername() throws Exception {
        assertEquals(testUser1.getUsername(), "username");
        testUser1.setUsername("newName");
        assertEquals(testUser1.getUsername(), "newName");
    }

    @Test
    public void getAndSetPassword() throws Exception {
        assertEquals(testUser1.getPassword(), "pass");
        testUser1.setPassword("newPass");
        assertEquals(testUser1.getPassword(), "newPass");
    }

    @Test
    public void getAndSetFullName() throws Exception {
        assertEquals(testUser1.getFullName(), "fullname");
        testUser1.setFullName("newName");
        assertEquals(testUser1.getFullName(), "newName");
    }

    @Test
    public void getAndSetRole() throws Exception {
        testUser1.setRole(UserRole.USER);
        assertEquals(testUser1.getRole(), UserRole.USER);
        testUser1.setRole(null);
        assertNull(testUser1.getRole());
    }

    @Test
    public void isLocked() throws Exception {
        assertFalse(testUser1.isLocked());
        testUser1.setLocked(true);
        assertTrue(testUser1.isLocked());

    }

    @Test
    public void getAndSetContacts() throws Exception {
        Contact contact = new Contact();
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        testUser1.setContacts(contactList);
        assertTrue(testUser1.getContacts().size()==1);

    }


    @Test
    public void equals() throws Exception {

        //reflexive
        assertEquals(testUser1,testUser1);
        //symmetric
        assertEquals(testUser1,testUser2);
        assertEquals(testUser2,testUser1);
        //transitive
        assertEquals(testUser1,testUser2);
        assertEquals(testUser2,testUser3);
        assertEquals(testUser1,testUser3);

        testUser1.setLocked(true);
        assertNotEquals(testUser1, testUser2);
        testUser2.setLocked(true);
        assertEquals(testUser1, testUser2);

        testUser1.setUsername("newName");
        assertNotEquals(testUser1, testUser2);
        testUser2.setUsername("newName");
        assertEquals(testUser1, testUser2);

        testUser1.setPassword("newPass");
        assertNotEquals(testUser1, testUser2);
        testUser2.setPassword("newPass");
        assertEquals(testUser1, testUser2);

        testUser1.setPassword(null);
        assertNotEquals(testUser1, testUser2);
        testUser2.setPassword(null);
        assertEquals(testUser1, testUser2);

        testUser1.setFullName("newName");
        assertNotEquals(testUser1, testUser2);
        testUser2.setFullName("newName");
        assertEquals(testUser1, testUser2);

        testUser1.setRole(null);
        assertNotEquals(testUser1, testUser2);

        assertNotEquals(testUser1, null);
        assertNotEquals(testUser1, new Integer(5));

    }

    @Test
    public void hashCodeTest() throws Exception {

        assertTrue(testUser1.hashCode() == testUser2.hashCode());
        testUser1.setId(4);
        assertTrue(testUser1.hashCode() == testUser2.hashCode());
        testUser2.setUsername("new");
        assertFalse(testUser1.hashCode() == testUser2.hashCode());

    }

    @Test
    public void toStringTest() throws Exception {
        User user = new User(1,"testName","pass","fullname",UserRole.USER,false,new ArrayList<>());
        String toString = user.toString();
        assertTrue(toString.contains("testName") &&
                toString.contains("pass") &&
                toString.contains("fullname"));
    }


}