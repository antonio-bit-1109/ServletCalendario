package controller;

import service.AppuntamentiService;
import service.UtentiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CancellaAppuntamento")
public class ServletCancellaAppuntamento extends HttpServlet {

    private AppuntamentiService appuntamentiService;

    public void setAppuntamentiService(AppuntamentiService appuntamentiService) {
        this.appuntamentiService = appuntamentiService;
    }

    //costrutt
    public ServletCancellaAppuntamento() throws SQLException {
        setAppuntamentiService(new AppuntamentiService());
    }

    public AppuntamentiService getAppuntamentiService() {
        return appuntamentiService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String mese = req.getParameter("mese");
            String anno = req.getParameter("anno");
            String giorno = req.getParameter("giorno");


            String idAppuntamento = req.getParameter("idApp");
            getAppuntamentiService().CancellaAppuntamento(idAppuntamento);
            // ritorna alla stessa pagina per vedere gli appuntamenti modificati.
            String redirectUrl = String.format("http://localhost:8080/CalendarioServlet/MostraAppuntamenti?mese=%s&anno=%s&giorno=%s", mese, anno, giorno);
            resp.sendRedirect(redirectUrl);
        } catch (SQLException e) {
            resp.sendError(500, "errore durante la cancellazione di un appuntamento.");
        }
    }
}
