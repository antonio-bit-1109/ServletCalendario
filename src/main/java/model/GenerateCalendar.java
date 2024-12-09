package model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class GenerateCalendar {

    // class containing all the method and utility items
    private UtilityCalendar utilCalen;

    public void setUtilCalen(UtilityCalendar utilCalen) {
        this.utilCalen = utilCalen;
    }

    //costrutt
    public GenerateCalendar() {
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

    // metodo per la generazione della logica del calendario
    public void GenerateCalendarMethod(int mese, int anno) throws IOException {

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


