package Main.Utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class PasswordHashingManager
{
    private final Random random = new SecureRandom();
    private static PasswordHashingManager instance = null;

    private PasswordHashingManager()
    {

    }

    public static PasswordHashingManager getInstance()
    {
        if(instance == null)
        {
            synchronized (PasswordHashingManager.class)
            {
                if (instance == null)
                {
                    instance = new PasswordHashingManager();
                }
            }
        }
        return instance;
    }

    public String getNewSalt()
    {
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        String salt = Base64.getEncoder().encodeToString(bytes);
        salt = salt.substring(0,16);
        return salt;
    }

    public String hashPassword(String password, String salt)
    {
        return Hashing.sha512().hashString(password + salt, StandardCharsets.UTF_8).toString();
    }
}
