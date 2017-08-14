package net.pylypchenko.entity;
import net.pylypchenko.entity.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Class for testing {@link Contact}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@RunWith(SpringRunner.class)
public class ContactTest {

    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    @Before
    public void setUp() throws Exception {
        contact1 = new Contact();
        contact2 = new Contact();
        contact3 = new Contact();
    }

    @Test
    public void toStringTest() throws Exception {
        Contact contact = new Contact(1,"LastName","firstName","middleName","111","222","address","@mail",new User());
        String toString = contact.toString();
        assertTrue(toString.contains("LastName")
                &&toString.contains("address")
                &&toString.contains("@mail"));
    }

    @Test
    public void equals() throws Exception {
        //reflexive
        assertEquals(contact1,contact1);
        //symmetric
        assertEquals(contact1,contact2);
        assertEquals(contact2,contact1);
        //transitive
        assertEquals(contact1,contact2);
        assertEquals(contact2,contact3);
        assertEquals(contact1,contact3);

        assertNotEquals(contact1, null);
        assertNotEquals(contact1, new Integer(5));


        contact1.setId(10);
        assertEquals(contact1, contact2);
        contact2.setId(10);
        assertEquals(contact1, contact2);


        contact1.setLastName("lastName");
        assertNotEquals(contact1, contact2);
        contact2.setLastName("lastName");
        assertEquals(contact1, contact2);

        contact1.setFirstName("firstName");
        assertNotEquals(contact1, contact2);
        contact2.setFirstName("firstName");
        assertEquals(contact1, contact2);

        contact1.setMiddleName("middleName");
        assertNotEquals(contact1, contact2);
        contact2.setMiddleName("middleName");
        assertEquals(contact1, contact2);

        contact1.setMobilePhone("333");
        assertNotEquals(contact1, contact2);
        contact2.setMobilePhone("333");
        assertEquals(contact1, contact2);

        contact1.setHomePhone("333");
        assertNotEquals(contact1, contact2);
        contact2.setHomePhone("333");
        assertEquals(contact1, contact2);

        contact1.setAddress("address");
        assertNotEquals(contact1, contact2);
        contact2.setAddress("address");
        assertEquals(contact1, contact2);

        contact1.setEmail("@mail");
        assertNotEquals(contact1, contact2);
        contact2.setEmail("@mail");
        assertEquals(contact1, contact2);

        User user = new User();
        user.setUsername("TestUser");
        contact1.setUser(user);
        assertNotEquals(contact1, contact2);
        contact2.setUser(user);
        assertEquals(contact1, contact2);
        contact1.setUser(null);
        assertNotEquals(contact1, contact2);
    }

    @Test
    public void hashCodeTest() throws Exception {

        assertTrue(contact1.hashCode() == contact2.hashCode());
        contact1.setId(10);
        assertTrue(contact1.hashCode() == contact2.hashCode());

        contact1.setLastName("lastName");
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setLastName("lastName");

        contact1.setFirstName("firstName");
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setFirstName("firstName");

        contact1.setMiddleName("middleName");
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setMiddleName("middleName");

        contact1.setMobilePhone("333");
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setMobilePhone("333");

        contact1.setHomePhone("333");
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setHomePhone("333");

        contact1.setAddress("address");
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setAddress("address");

        contact1.setEmail("@mail");
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setEmail("@mail");

        contact1.setUser(null);
        assertTrue(contact1.hashCode() == contact2.hashCode());
        contact1.setUser(new User());
        assertFalse(contact1.hashCode() == contact2.hashCode());
        contact2.setUser(new User());
        assertTrue(contact1.hashCode() == contact2.hashCode());
    }

}