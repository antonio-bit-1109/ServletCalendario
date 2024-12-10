package controller;

import service.UtentiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create")
public class ServletCreaNewUtente extends HttpServlet {

    private UtentiService utentiService;

    public void setUtentiService(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    //costrutt
    public ServletCreaNewUtente() throws SQLException {
        setUtentiService(new UtentiService());
    }

    public UtentiService getUtentiService() {
        return utentiService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String username = req.getParameter("creauser");
            String pass = req.getParameter("creapass");


            getUtentiService().creaNuovoUtente(username, pass);
            req.setAttribute("msgSucc", "utente creato con successo");
            req.getRequestDispatcher("loginSuccess.jsp").forward(req, resp);
            //resp.sendRedirect("http://localhost:8080/CalendarioServlet/loginSuccess.jsp?msg=utente%20creato%20con%20successo");
        } catch (SQLException e) {
            resp.sendError(500, String.valueOf(e));
        }

    }
}
