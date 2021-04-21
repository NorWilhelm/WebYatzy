package servlets;

import dao.ScoreCardDao;
import dao.UserDao;
import model.ScoreCard;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserDao userDao;



    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {




        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String db_password = null;
        List<User> users = userDao.findAll();
        boolean userExists = false;
        boolean password_match = false;
        for (User user : users){
            System.out.println(user.getUsername());
            if(user.getUsername().compareTo(username) == 0){
                userExists = true;
                password_match = user.getPassword().equals(password);
                break;
            }
        }

        if (userExists && password_match){

            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(username);
            request.getRequestDispatcher("lobbies.jsp")
                    .forward(request, response);

        } else{
            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write("error");

            System.out.println("Username does not exist or bad password");
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }


    }
}