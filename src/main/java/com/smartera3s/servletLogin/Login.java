package com.smartera3s.servletLogin;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/signin")

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LoginMongodb db = new LoginMongodb();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("password"));
        String response = db.sendRes(req.getParameter("name"), req.getParameter("password"));
        PrintWriter pw=res.getWriter();
        String msg = "tmam";
        String json = new Gson().toJson(msg);

        if (response.equals("true")){
            System.out.println("signed in");
            //res.setStatus(200);
            sendJson("logged in successfully",pw);

        }

        else if (response.equals("false")){
            System.out.println("signed in");
            //res.setStatus(401);
            sendJson("email or password doesn't match",pw);
        }
        else {
            sendJson("Fill in the required fields",pw);
        }
    }
    public void sendJson(String msg,PrintWriter pw){
        String json = new Gson().toJson(msg);
        pw.write(json);
        pw.close();

    }




}