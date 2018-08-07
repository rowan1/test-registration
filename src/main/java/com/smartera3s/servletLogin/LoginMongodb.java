package com.smartera3s.servletLogin;

import java.util.HashMap;
import java.util.Map;

public class LoginMongodb {
    DataAccessManager dbManager = DataAccessManager.getInstance();
    public static String sendRes(String name, String password) {

        Map<String, String> keys = new HashMap<>();
        keys.put("username", name);
        keys.put("password", password);

        if (name.equals("") || password.equals("")){

            return "Fill in the required fields";
        }

        else {
            return !dbManager.check(keys).toString();
        }
    }

}
