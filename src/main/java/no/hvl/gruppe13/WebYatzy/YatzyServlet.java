package no.hvl.gruppe13.WebYatzy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static no.hvl.gruppe13.WebYatzy.UrlMappings.*;

@WebServlet("/" + YATZY_URL)
public class YatzyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(LOGIN_URL + "?requiresLogin");
        } else {
            request.setAttribute("YATZY_URL", YATZY_URL);
            request.setAttribute("LOGOUT_URL", LOGOUT_URL);
            request.getRequestDispatcher("yatzy.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesjon = request.getSession(false);

        if (sesjon == null || sesjon.getAttribute("username") == null) {
            response.sendRedirect(LOGIN_URL + "?requiresLogin");
        } else {

            Cup cup = new Cup(1);
            cup.roll();

            // TODO: Do something with the dice roll.
            response.sendRedirect(YATZY_URL);
        }
    }
}
