import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Authentication {
    private static final HashMap<String, String> userDatabase = new HashMap<>();

    static {
        // Pre-populate with a user for testing
        userDatabase.put("user", hashPassword("password"));
    }

    public static boolean verifyCredentials(String username, String password) {
        String hashedPassword = userDatabase.get(username);
        return hashedPassword != null && hashedPassword.equals(hashPassword(password));
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found");
        }
    }
}