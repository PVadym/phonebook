package net.pylypchenko.enums;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Class for testing {@link UserRole}.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
public class UserRoleTest {

    @Test
    public void valuesTest(){
        String toString = Arrays.toString(UserRole.values());
        assertTrue(toString.contains("user".toUpperCase())
                &&toString.contains("admin".toUpperCase()));
    }

    @Test
    public void valueOfStringTest(){
        assertEquals(UserRole.USER, UserRole.valueOf("USER"));
        assertEquals(UserRole.ADMIN, UserRole.valueOf("ADMIN"));
    }

}