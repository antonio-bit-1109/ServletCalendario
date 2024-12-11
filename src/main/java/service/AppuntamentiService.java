package service;

import dao.AppuntamentoDAO;
import dao.DataDAO;
import dto.AppuntamentoDTO;
import dto.UtenteDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppuntamentiService {

    private AppuntamentoDAO appuntamentoDAO;

    public void setAppuntamentoDAO(AppuntamentoDAO appuntamentoDAO) {
        this.appuntamentoDAO = appuntamentoDAO;
    }

    //costrutt
    public AppuntamentiService() throws SQLException {
        setAppuntamentoDAO(new AppuntamentoDAO());
    }

    public AppuntamentoDAO getAppuntamentoDAO() {
        return appuntamentoDAO;
    }

    public ArrayList<AppuntamentoDTO> ottieniTuttiAppuntamentiGiorno(String mese, String anno, String giorno, UtenteDTO user) throws SQLException {
        return getAppuntamentoDAO().getAllAppuntamentiDelGiorno(mese, anno, giorno, user);
    }

    public void AggiungiAppuntamento(UtenteDTO user, DataDAO data, String orarioApp, String descrizioneApp) throws SQLException {

        String idUtente = user.getId();

        getAppuntamentoDAO().insertNewAppuntamento(idUtente, data, orarioApp, descrizioneApp);
    }
}
