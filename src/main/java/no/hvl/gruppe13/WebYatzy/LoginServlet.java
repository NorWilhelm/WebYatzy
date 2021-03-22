package no.hvl.gruppe13.WebYatzy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import static no.hvl.gruppe13.WebYatzy.UrlMappings.LOGIN_URL;
import static no.hvl.gruppe13.WebYatzy.UrlMappings.YATZY_URL;

@WebServlet("/" + LOGIN_URL)
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String loginMessage = "";

        if (request.getParameter("requiresLogin") != null) {
            loginMessage = "Forespørselen din krever pålogging. "
                    + "(Du kan ha blitt logget ut automatisk)";

            // TODO: Should check username and password, and not specify which is wrong to make hacking by crosschecking impossible
        } else if (request.getParameter("invalidUsername") != null) {
            loginMessage = "Manglende eller ugyldig brukernavn";
        }

        request.setAttribute("loginMessage", loginMessage);

        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");

        if (username == null || !Validator.isValidUsername(username)) {
            response.sendRedirect(LOGIN_URL + "?invalidUsername");
        } else {

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
            session.setMaxInactiveInterval(10);

            session.setAttribute("username", username);
            // sesjon.setAttribute("score", new Score());

            response.sendRedirect(YATZY_URL);
        }
    }
}