package com.smartera3s.servletSignup;
import com.smartera3s.mongodb.DataAcessManager;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SignupMongodb {
   public static DataAcessManager dbManager = DataAcessManager.getInstance();

    public static String sendRes(String name, String password) {

        HttpServletResponse res = null;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        Map<String, String>  keys = new HashMap<>();
        keys.put("username", name);
        keys.put("password", password);
        if (name.equals("") || password.equals("")) {

            return "Fill in the required fields";
        }

        if (!checkAlphabetic(name)) {
            return "Enter the valid name";
        }
        else if ((password.length() < 6) || (!hasUppercase)) {
            return "Password must be 6 or more and has UpperCase letter";
        }
        else {
            return String.valueOf(dbManager.insert(keys));
        }
    }

    public static boolean checkAlphabetic(String input) {
        for (int i = 0; i != input.length(); ++i) {
            if (!Character.isLetter(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}