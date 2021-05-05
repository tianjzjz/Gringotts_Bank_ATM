package BankAttributes;

import java.util.UUID;

/**
 * This class is responsible for generating a random ID for an account.
 */
public class IDCenter {
    public IDCenter() {}

    public static String randomID() {
        UUID uuid = UUID.randomUUID();

        String res = uuid.toString().replace("-", "").substring(0, 8);

        return res;
    }
}
