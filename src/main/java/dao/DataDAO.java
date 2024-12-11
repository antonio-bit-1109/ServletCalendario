package dao;

public class DataDAO {

    private String giorno;
    private String mese;
    private String anno;

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public void setMese(String mese) {
        this.mese = mese;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    //costrutt vuoto
    public DataDAO() {
    }


    public String getAnno() {
        return anno;
    }

    public String getMese() {
        return mese;
    }

    public String getGiorno() {
        return giorno;
    }
}
