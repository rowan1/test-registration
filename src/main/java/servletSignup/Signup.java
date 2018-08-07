package servletSignup;
import com.google.gson.Gson;
import servletLogin.LoginMongodb;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/signup")

public class Signup extends HttpServlet {
    private static final long serialVersionUID = 1L;
    SignupMongodb db = new SignupMongodb();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("inside receiving signup request");
        String responsereturned = "";
        PrintWriter pw=response.getWriter();

        if (checknull(request.getParameter("name")) && checknull(request.getParameter("password"))){
            System.out.println(request.getParameter("name"));
            System.out.println(request.getParameter("password"));
            responsereturned = db.sendRes(request.getParameter("name"), request.getParameter("password"));
            System.console().writer().println("signed up");

            if (responsereturned.equals("True")){
                //response.setStatus(200);

                System.out.println("registered");
                sendJson("registered",pw);

            }

//            else if (responsereturned.equals("False")){
//                //response.setStatus(401);
//                System.out.println("sayb 7agat 8lt");
//                sendJson("fill in the required fields",pw);
//            }

            else {

                System.out.println("sayb 7aga");
                sendJson(responsereturned,pw);
            }
        }
        else{

            System.out.println("null exception");
            sendJson("null exception",pw);
        }
    }

    public boolean checknull(String input) {

        if(input != null) {

            return true;
        }
        else {
            return false;
        }
    }

    public void sendJson(String msg,PrintWriter pw){
        String json = new Gson().toJson(msg);
        pw.write(json);
        pw.close();

    }
}