package servletLogin;

import javax.servlet.http.HttpServletResponse;

public class LoginMongodb {
    public static String sendRes(String name, String password) {

        if (name.equals("") || password.equals("")){

            return "Fill in the required fields";
        }

        else {

                if (name.matches("rowan") && password.matches("1234"))
                    return "True";
                else
                    return "False";
            }
    }

        public static String dataFetched () {

            return "Hello World";
        }

}
