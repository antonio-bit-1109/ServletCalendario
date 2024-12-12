package service;

import dao.AppuntamentoDAO;
import dao.DataDAO;
import dto.AppuntamentoDTO;
import dto.UtenteDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void CancellaAppuntamento(String idAppuntamento) throws SQLException {
        int idAppuntam = Integer.parseInt(idAppuntamento);
        getAppuntamentoDAO().DeleteAppuntamentoDb(idAppuntam);
    }

    public void ModificaAppuntamento(String idApp, String giorno, String mese, String anno, String nuovaOra, String nuovaDesc) throws SQLException {
        int idAppunt = Integer.parseInt(idApp);
        getAppuntamentoDAO().ModificaAppuntamentoDb(idAppunt, giorno, mese, anno, nuovaOra, nuovaDesc);
    }

    public HashMap<Integer, Integer> GetImpegniMese(String anno, String mese) throws SQLException {
        List<String> listaImpegni = getAppuntamentoDAO().GetAppuntamentiDelMese(anno, mese);
        // la lista ritorna con valori duplicati {1,1,1,2,3,3...)
        // quei valori rappresentano il numero di appuntamenti del giorno n specificato
        // (il giorno 1° del mese 3 appuntamenti) e cosi via

        // inizializzo una mappa dove la chiave identifica il giorno,
        // e il value è un arraylist di valori contenenti la quantità di appuntamenti per quel giorno
        HashMap<Integer, Integer> densitaAppuntam = new HashMap<>();
        int n = 1;
        for (int i = 0; i < listaImpegni.size(); i++) {
            int val = Integer.parseInt(listaImpegni.get(i));

            try {

                if (Integer.parseInt(listaImpegni.get(i)) != Integer.parseInt(listaImpegni.get(i + 1))) {
                    densitaAppuntam.put(val, n);
                    n = 1;
                    continue;
                }

            } catch (IndexOutOfBoundsException ex) {

                densitaAppuntam.put(val, n);
                n = 1;
                break;
            }


            if (densitaAppuntam.containsKey(val)) {
                n++;
            } else {
                densitaAppuntam.put(val, null);
                n++;
            }

        }

        return densitaAppuntam;

    }
}
