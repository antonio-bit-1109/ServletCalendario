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

@WebServlet("/MostraAppuntamenti")
public class ServletMostraAppuntamenti extends HttpServlet {

    private AppuntamentiService appuntamentiService;

    public void setAppuntamentiService(AppuntamentiService appuntamentiService) {
        this.appuntamentiService = appuntamentiService;
    }

    //costrutt
    public ServletMostraAppuntamenti() throws SQLException {
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

            String mese = req.getParameter("mese");
            String anno = req.getParameter("anno");
            String giorno = req.getParameter("giorno");

            ArrayList<AppuntamentoDTO> listaAppuntamenti = getAppuntamentiService().ottieniTuttiAppuntamentiGiorno(mese, anno, giorno, user);

            req.setAttribute("listaAppuntamenti", listaAppuntamenti);
            req.getRequestDispatcher("/protected/VisualizzaAppuntamenti.jsp").forward(req, resp);
            listaAppuntamenti.clear();

        } catch (RuntimeException | SQLException e) {
            resp.sendError(500, String.valueOf(e));
        }
        // ritorna una jsp nel quale visualizzo appuntamenti fissati per questo giorno
        // ed un form nel quale poter inserire un nuovo appuntamento
    }
}
