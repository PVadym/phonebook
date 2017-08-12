package net.pylypchenko.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Вадим on 12.08.2017.
 */
public class IdGenerator {

    public static int getId(){
        UUID idOne = UUID.randomUUID();
        int uid=idOne.toString().hashCode();

        String key = String.valueOf(uid).replaceAll("-", "");

        return Integer.parseInt(key);
    }
}
