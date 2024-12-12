package controller;

import service.AppuntamentiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ModificaAppuntamento")
public class ServletModificaAppuntamento extends HttpServlet {


    private AppuntamentiService appuntamentiService;

    public void setAppuntamentiService(AppuntamentiService appuntamentiService) {
        this.appuntamentiService = appuntamentiService;
    }

    //costrutt
    public ServletModificaAppuntamento() throws SQLException {
        setAppuntamentiService(new AppuntamentiService());
    }

    public AppuntamentiService getAppuntamentiService() {
        return appuntamentiService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String idApp = req.getParameter("idApp");
            String giorno = req.getParameter("giorno");
            String mese = req.getParameter("mese");
            String anno = req.getParameter("anno");
            String nuovaOra = req.getParameter("nuovaOra");
            String nuovaDescrizione = req.getParameter("nuovaDescrizione");

            getAppuntamentiService().ModificaAppuntamento(idApp, giorno, mese, anno, nuovaOra, nuovaDescrizione);

            String redirectUrl = String.format("http://localhost:8080/CalendarioServlet/MostraAppuntamenti?mese=%s&anno=%s&giorno=%s", mese, anno, giorno);
            resp.sendRedirect(redirectUrl);

        } catch (RuntimeException | SQLException e) {
            resp.sendError(500, String.valueOf(e));
        }


    }
}
