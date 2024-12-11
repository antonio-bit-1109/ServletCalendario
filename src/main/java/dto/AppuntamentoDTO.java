package dto;

import java.sql.Time;
import java.util.Date;

public class AppuntamentoDTO {

    private int idUtente;
    private Date data;
    private Time ora;
    private String descrizione;

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //costrutt VUOTO
    public AppuntamentoDTO() {
    }

    public int getIdUtente() {
        return idUtente;
    }

    public Date getData() {
        return data;
    }

    public Time getOra() {
        return ora;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
