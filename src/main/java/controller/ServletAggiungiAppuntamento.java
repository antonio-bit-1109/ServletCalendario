package controller;

import dao.DataDAO;
import dto.UtenteDTO;
import service.AppuntamentiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/AggiungiAppuntamento")
public class ServletAggiungiAppuntamento extends HttpServlet {

    private AppuntamentiService appuntamentiService;

    public void setAppuntamentiService(AppuntamentiService appuntamentiService) {
        this.appuntamentiService = appuntamentiService;
    }

    //costrutt
    public ServletAggiungiAppuntamento() throws SQLException {
        setAppuntamentiService(new AppuntamentiService());
    }

    public AppuntamentiService getAppuntamentiService() {
        return appuntamentiService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            HttpSession Session = req.getSession();
            UtenteDTO user = (UtenteDTO) Session.getAttribute("user");

            // String dataApp = req.getParameter("inputData");
            String mese = req.getParameter("mese");
            String anno = req.getParameter("anno");
            String giorno = req.getParameter("giorno");

            String orarioApp = req.getParameter("inputOra");
            String descrizioneApp = req.getParameter("inputDesc");

            DataDAO data = new DataDAO();
            data.setAnno(anno);
            data.setMese(mese);
            data.setGiorno(giorno);

            getAppuntamentiService().AggiungiAppuntamento(user, data, orarioApp, descrizioneApp);
            String redirectUrl = String.format("http://localhost:8080/CalendarioServlet/MostraAppuntamenti?mese=%s&anno=%s&giorno=%s", mese, anno, giorno);
            resp.sendRedirect(redirectUrl);
        } catch (RuntimeException | SQLException ex) {
            resp.sendError(500, ex.toString());
        }
    }
}
