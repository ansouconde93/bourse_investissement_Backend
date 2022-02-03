package bourse.bourse.project.security;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SecurityConstante {
    private final static String getSecret() {
        SecureRandom rnd = new SecureRandom();
        rnd.setSeed(1234);
        byte[] token = new byte[1000];
        rnd.nextBytes(token);
        return new BigInteger(1, token).toString(16);
    }
    public static final String SECRET = getSecret();
    public static final long EXPIRATION_TIME = 864_000_000;// refresh token espire après 10 jours de sa creation
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING ="Authorization";
}
