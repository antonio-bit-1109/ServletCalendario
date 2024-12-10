package controller;

import service.UtentiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class ServletDeleteUtente extends HttpServlet {

    private UtentiService utentiService;

    public void setUtentiService(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    //costrutt
    public ServletDeleteUtente() throws SQLException {
        setUtentiService(new UtentiService());
    }

    public UtentiService getUtentiService() {
        return utentiService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            String username = req.getParameter("usernameDel");
            String psw = req.getParameter("passDel");

            getUtentiService().CancellaUtente(username, psw);
            req.setAttribute("msgDeleteSucc", "utente cancellato con successo");
            req.getRequestDispatcher("loginSuccess.jsp").forward(req, resp);
            
        } catch (RuntimeException | SQLException e) {
            resp.sendError(500, String.valueOf(e));
        }

    }
}
