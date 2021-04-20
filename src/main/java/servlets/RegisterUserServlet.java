package servlets;

import dao.GameDao;
import dao.UserDao;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "registerServlet", value = "/register")
public class RegisterUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("User " + username + "Password" + password );
        List<User> users = userDao.findAll();
        System.out.println("Fatal error after db fetch query");
        boolean userExists = false;
        for (User user : users){
            if(user.getUsername().compareTo(username) == 0){
                userExists = true;
                break;
            }
        }

        if (userExists){
            System.out.println("Username taken");
            // TODO redirect to login with error message
        } else{
            User newUser = new User(username, password);
            userDao.createUser(newUser);
        }


    }
}