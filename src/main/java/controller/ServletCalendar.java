package controller;


import model.GenerateCalendar;
import service.AppuntamentiService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/generaCale")
public class ServletCalendar extends HttpServlet {

    private GenerateCalendar generateCalendar;
    private AppuntamentiService appuntamentiService;

    public void setAppuntamentiService(AppuntamentiService appuntamentiService) {
        this.appuntamentiService = appuntamentiService;
    }

    public void setGenerateCalendar(GenerateCalendar generateCalendar) {
        this.generateCalendar = generateCalendar;
    }

    // costrutt
    public ServletCalendar() throws SQLException {
        setGenerateCalendar(new GenerateCalendar());
        setAppuntamentiService(new AppuntamentiService());
    }

    public AppuntamentiService getAppuntamentiService() {
        return appuntamentiService;
    }

    public GenerateCalendar getGenerateCalendar() {
        return generateCalendar;
    }

    // ricavo la matrice popolata
    public int[][] getMatrix() {
        return getGenerateCalendar().getUtilCalen().getMatrix();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String mese = req.getParameter("mese");
            String anno = req.getParameter("anno");

            int meseNum = Integer.parseInt(mese);
            int annoNum = Integer.parseInt(anno);

            if (meseNum < 1 || meseNum > 12) {

                String ErrorMese = "Il mese deve avere un valore compreso tra 1 e 12.";
                req.setAttribute("ErrorMese", ErrorMese);
//                resp.sendError(400, "hai inserito un mese non valido. Inserisci una valore compreso tra 1 e 12");
                req.getRequestDispatcher("/protected/formCalendar.jsp").forward(req, resp);
                return;
            }

            if (annoNum < 1920) {
                String ErrorAnno = "L'anno non deve essere inferiore al 1920.";
//                resp.sendError(400, "la data non può essere inferiore al 1920.");
                req.setAttribute("ErrorAnno", ErrorAnno);
                req.getRequestDispatcher("/protected/formCalendar.jsp").forward(req, resp);
                return;
            }


            // metodo per la generazione del calendario.
            getGenerateCalendar().GenerateCalendarMethod(meseNum, annoNum);

            // ritorna il numero di impegni del mese corrente
            HashMap<Integer, Integer> densitaImpegniMese = getAppuntamentiService().GetImpegniMese(anno, mese);

            String meseString = getGenerateCalendar().getUtilCalen().getMesiArr()[meseNum - 1];

            // Aggiungi i dati al contesto della richiesta
            req.setAttribute("densitaImpegniMese", densitaImpegniMese);
            req.setAttribute("matrix", getMatrix());
            req.setAttribute("mese", meseNum);
            req.setAttribute("anno", annoNum);
            req.setAttribute("meseString", meseString);

            // Effettua il dispatching verso la JSP
            req.getRequestDispatcher("/protected/presentation.jsp").forward(req, resp);

            // reset delle variabili in utility
            getGenerateCalendar().getUtilCalen().ResetAll();

        } catch (RuntimeException | SQLException | ServletException e) {

            resp.sendError(500, String.valueOf(e));

        }

    }
}

