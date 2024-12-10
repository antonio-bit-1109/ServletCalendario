package controller;

import service.UtentiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/editPswUser")
public class ServletUpdatePassUtente extends HttpServlet {

    private UtentiService utentiService;

    public void setUtentiService(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    //costrutt
    public ServletUpdatePassUtente() throws SQLException {
        setUtentiService(new UtentiService());
    }

    public UtentiService getUtentiService() {
        return utentiService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession();

            String username = req.getParameter("username");
            String oldPass = req.getParameter("oldPass");
            String newPass = req.getParameter("newPass");

            String nomeUtenteSession = (String) session.getAttribute("nomeUtente");

            if (nomeUtenteSession == null) {
                throw new RuntimeException("nessun nome utente salvato in sessione.");
            }

            getUtentiService().EditPasswordUtente(username, oldPass, newPass, nomeUtenteSession);

            req.setAttribute("editPswSucc", "password modificata con successo");
            req.getRequestDispatcher("loginSuccess.jsp").forward(req, resp);
        } catch (RuntimeException | SQLException e) {
            resp.sendError(500, String.valueOf(e));
        }
    }
}
