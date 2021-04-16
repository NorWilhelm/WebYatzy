

import dao.GameDao;
import dao.UserDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "startPageServlet", value = "/")
public class StartPageServlet extends HttpServlet {


    @EJB
    private GameDao gameDao;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        //request.setAttribute("games", gameDao.findAll());


        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}