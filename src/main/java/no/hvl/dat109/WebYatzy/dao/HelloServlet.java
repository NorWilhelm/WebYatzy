package no.hvl.dat109.WebYatzy.dao;


import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

//    public void init() {
//        message = "Hello World!";
//    }

    @EJB
    private PersonDao personDao;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");

        request.setAttribute("persons", personDao.findAll());


        RequestDispatcher dispatcher = request.getRequestDispatcher("Test.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}