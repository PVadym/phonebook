package net.pylypchenko.utils;

import java.util.UUID;


/**
 * Class provides the method for creating unique identifier for entities
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
public class IdGenerator {

    /**
     * The method generates unique identifier
     *
     * @return Generated unique identifier
     */
    public static int getId() {
        UUID idOne = UUID.randomUUID();
        int uid = idOne.toString().hashCode();
        String key = String.valueOf(uid).replaceAll("-", "");
        return Integer.parseInt(key);
    }
}
