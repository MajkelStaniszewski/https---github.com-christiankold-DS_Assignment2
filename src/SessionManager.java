import java.util.HashMap;
import java.util.UUID;

public class SessionManager {
    private static final HashMap<String, Long> activeSessions = new HashMap<>();
    private static final long SESSION_TIMEOUT = 600000; // 10 minutes

    public static String createSession(String username) {
        String sessionToken = UUID.randomUUID().toString();
        activeSessions.put(sessionToken, System.currentTimeMillis());
        return sessionToken;
    }

    public static boolean isSessionValid(String sessionToken) {
        Long startTime = activeSessions.get(sessionToken);
        if (startTime == null) return false;

        if (System.currentTimeMillis() - startTime > SESSION_TIMEOUT) {
            activeSessions.remove(sessionToken);
            return false;
        }
        return true;
    }

    public static void invalidateSession(String sessionToken) {
        activeSessions.remove(sessionToken);
    }
}