package servletcalendar;


import util.UtilityCalendar;

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

    // class containing all the method and utility items
    private UtilityCalendar utilCalen;

    public void setUtilCalen(UtilityCalendar utilCalen) {
        this.utilCalen = utilCalen;
    }

    //costrutt
    public ServletCalendar() {
        setUtilCalen(new UtilityCalendar(
                1,
                false,
                new String[]{"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio",
                        "Giugno", "Luglio", "Agosto", "Settembre",
                        "Ottobre", "Novembre", "Dicembre"},
                new int[6][7]
        ));
    }

    public UtilityCalendar getUtilCalen() {
        return utilCalen;
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

        GenerateCalendar(meseNum, annoNum, resp);

        // Aggiungi i dati al contesto della richiesta
        req.setAttribute("matrix", getUtilCalen().getMatrix());
        req.setAttribute("mese", meseNum);
        req.setAttribute("anno", annoNum);
        req.setAttribute("meseString", getUtilCalen().getMesi()[meseNum - 1]);

        // Effettua il dispatching verso la JSP
        req.getRequestDispatcher("presentation.jsp").forward(req, resp);

    }

    // metodo per la generazione della logica del calendario
    private void GenerateCalendar(int mese, int anno, HttpServletResponse resp) throws IOException {

        // ritorna qual'è il primo giorno del mese del tipo enum (MONDAY , THUESDAY , ...FRIDAY ECC ECC  )
        LocalDate dayOne = LocalDate.of(anno, mese, 1);
        DayOfWeek dayOfweek = dayOne.getDayOfWeek();


        // ritorna quanti giorni totali ha il mese selezionato
        int daysInMonth = dayOne.lengthOfMonth();
        getUtilCalen().setLastDay(daysInMonth);


        // ciclare la matrice per inserire i valori in matrice
        for (int i = 0; i < utilCalen.getMatrix().length; i++) {
            //sb.append("<tr>");
            int[] row = utilCalen.getMatrix()[i];

            // ogni row ha 7 giorni
            for (int j = 0; j < row.length; j++) {

                // elaboro la prima riga della matrice e la assegno;
                if (!getUtilCalen().IsFirstDayFound()) {
                    getUtilCalen().setFirstRow(DecideFirstDay(dayOfweek));

                    utilCalen.getMatrix()[0] = getUtilCalen().getFirstRow();
                    break;
                }

                // elaborare le righe successive per popolare in toto la matrice

                // quando arrivo all'ultimo giorno del calendario interrompo il ciclo
                if (getUtilCalen().getCurrDay() > getUtilCalen().getLastDay()) {
                    break;
                }


                utilCalen.getMatrix()[i][j] = getUtilCalen().getCurrDay();
                getUtilCalen().AddOneDay();


            }

        }

    }

    //populate array that will be first row of the matrix a partire dallo starting point
    private int[] DecideFirstDay(DayOfWeek dayofweek) {
        int k = dayofweek.getValue();
        int[] arrROW = new int[7];

        for (int i = k - 1; i < arrROW.length; i++) {

            arrROW[i] = getUtilCalen().getCurrDay();
            getUtilCalen().AddOneDay();

        }
        getUtilCalen().setFirstDayFound(true);
        return arrROW;
    }
}
