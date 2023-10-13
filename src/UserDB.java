import java.util.HashMap;

public class UserDB {
    public static HashMap<String, String> userDB = new HashMap<>();
    public static void addUser (String login, String haslo){
        userDB.put(login, haslo);
    }
}
