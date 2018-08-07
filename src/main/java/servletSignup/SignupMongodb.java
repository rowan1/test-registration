package servletSignup;
import javax.servlet.http.HttpServletResponse;

public class SignupMongodb {

    public static String sendRes(String name, String password) {

        HttpServletResponse res = null;
        boolean hasUppercase = !password.equals(password.toLowerCase());

        if (name.equals("") || password.equals("")) {

            return "Fill in the required fields";
        }

        if (!checkAlphabetic(name)) {
            return "Enter the valid name";
        }
        else if ((password.length() < 6) || (!hasUppercase)) {
            return "Password must be 6 or more and has UpperCase letter";
        }
        else if (!checkExistance(name)){
            return "You are already registered";

        }
        else {
            insert(name, password);
            //Registered Successfully
            return "True";
        }
    }

    public static void insert(String name, String password) {
        if (name.equals("")) {
            String newName = name;
            String newPassword = password;
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

    public static boolean checkExistance(String name){
        return true;
    }

}