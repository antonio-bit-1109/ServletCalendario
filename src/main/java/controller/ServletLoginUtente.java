package controller;

import dto.UtenteDTO;
import service.UtentiService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class ServletLoginUtente extends HttpServlet {

    private UtentiService utentiService;

    public void setUtentiService(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    //costrutt
    public ServletLoginUtente() throws SQLException {
        setUtentiService(new UtentiService());
    }

    public UtentiService getUtentiService() {
        return utentiService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/protected/formCalendar.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession();
            String Username = req.getParameter("username");
            String psw = req.getParameter("password");

            UtenteDTO utente = getUtentiService().login(Username, psw);
            // dopo la login salvo username dell utente in una sessione.
            if (session.getAttribute("user") == null) {
                session.setAttribute("user", utente);
            }

            // resp.sendRedirect("http://localhost:8080/CalendarioServlet/webapp/WEB-INF/protected/formCalendar.jsp");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/protected/formCalendar.jsp");
            dispatcher.forward(req, resp);

        } catch (RuntimeException | SQLException | ServletException e) {
            resp.sendError(500, String.valueOf(e));

        }
    }
}
