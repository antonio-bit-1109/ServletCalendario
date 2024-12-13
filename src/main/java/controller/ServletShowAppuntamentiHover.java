package controller;

import dto.AppuntamentoDTO;
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
import java.util.ArrayList;

@WebServlet("/AppuntamentiFetch")
public class ServletShowAppuntamentiHover extends HttpServlet {

    private AppuntamentiService appuntamentiService;

    public void setAppuntamentiService(AppuntamentiService appuntamentiService) {
        this.appuntamentiService = appuntamentiService;
    }

    //costrutt
    public ServletShowAppuntamentiHover() throws SQLException {
        setAppuntamentiService(new AppuntamentiService());
    }

    public AppuntamentiService getAppuntamentiService() {
        return appuntamentiService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession Session = req.getSession();
            UtenteDTO user = (UtenteDTO) Session.getAttribute("user");


            String anno = req.getParameter("anno");
            String mese = req.getParameter("mese");
            String giorno = req.getParameter("giorno");

            ArrayList<AppuntamentoDTO> listaAppuntamentiTemp = getAppuntamentiService().ottieniTuttiAppuntamentiGiorno(mese, anno, giorno, user);

            req.setAttribute("listaAppuntamentiTemp", listaAppuntamentiTemp);
            req.getRequestDispatcher("/protected/VisualizzaAppuntamenti.jsp").forward(req, resp);
            listaAppuntamentiTemp.clear();


        } catch (RuntimeException | SQLException ex) {

            resp.sendError(500, String.valueOf(ex));
        }
    }
}
