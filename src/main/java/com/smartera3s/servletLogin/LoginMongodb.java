package com.smartera3s.servletLogin;
import com.smartera3s.mongodb.DataAcessManager;

import java.util.HashMap;
import java.util.Map;

public class LoginMongodb {
    public static DataAcessManager dbManager = DataAcessManager.getInstance();

    public static String sendRes(String name, String password) {

        Map<String, String> keys = new HashMap<>();
        keys.put("username", name);
        keys.put("password", password);
        System.out.println(String.valueOf(dbManager.check(keys)));
        if (name.equals("") || password.equals("")){

            return "Fill in the required fields";
        }

        else {
            return String.valueOf(!dbManager.check(keys));
        }
    }

}
