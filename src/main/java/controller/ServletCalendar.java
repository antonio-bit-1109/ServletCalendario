package controller;


import model.GenerateCalendar;
import model.UtilityCalendar;
import service.UtentiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

@WebServlet("/generaCale")
public class ServletCalendar extends HttpServlet {

    private GenerateCalendar generateCalendar;
    
    public void setGenerateCalendar(GenerateCalendar generateCalendar) {
        this.generateCalendar = generateCalendar;
    }

    // costrutt
    public ServletCalendar() {
        setGenerateCalendar(new GenerateCalendar());

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

        String mese = req.getParameter("mese");
        String anno = req.getParameter("anno");

        int meseNum = Integer.parseInt(mese);
        int annoNum = Integer.parseInt(anno);

        if (meseNum < 1 || meseNum > 12) {
            resp.sendError(400, "hai inserito un mese non valido. Inserisci una valore compreso tra 1 e 12");
            return;
        }

        if (annoNum < 1920) {
            resp.sendError(400, "la data non può essere inferiore al 1920.");
            return;
        }

        //GenerateCalendar(meseNum, annoNum, resp);
        getGenerateCalendar().GenerateCalendarMethod(meseNum, annoNum);

        // String meseString = "mese default"; //getGenerateCalendar().getUtilCalen().CalculateMeseString();
        String meseString = getGenerateCalendar().getUtilCalen().getMesiArr()[meseNum - 1];

        // Aggiungi i dati al contesto della richiesta
        req.setAttribute("matrix", getMatrix());
        req.setAttribute("mese", meseNum);
        req.setAttribute("anno", annoNum);
        req.setAttribute("meseString", meseString);

        // Effettua il dispatching verso la JSP
        req.getRequestDispatcher("presentation.jsp").forward(req, resp);

        // reset delle variabili in utility
        getGenerateCalendar().getUtilCalen().ResetAll();
    }
}

