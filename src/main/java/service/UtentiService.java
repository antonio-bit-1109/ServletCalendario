package service;

import dao.UtentiDAO;
import dto.UtenteDTO;

import java.sql.SQLException;

// logica di business del login
public class UtentiService {

    private UtentiDAO utentiDao;

    public void setUtentiDao(UtentiDAO utentiDao) {
        this.utentiDao = utentiDao;
    }

    //costrutt
    public UtentiService() throws SQLException {
        setUtentiDao(new UtentiDAO());
    }

    public UtentiDAO getUtentiDao() {
        return utentiDao;
    }

    // metodo per implementare il login
    public UtenteDTO login(String username, String password) throws SQLException {
        UtenteDTO user = getUtentiDao().GetUtente(username);


        if (!user.getUsername().equals(username)) {
            throw new RuntimeException("username non corrisponde.");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("la password non corrisponde.");
        }

        return user;
    }

    // metodo per creare nuovo utente
    public void creaNuovoUtente(String username, String password) throws SQLException {
        int RowInserted = getUtentiDao().InsertNewUtente(username, password);

        if (RowInserted != 1) {
            throw new RuntimeException("errore durante l 'inserimento di un nuovo utente.");
        }
    }


    // metodo per modifica password utente
    public void EditPasswordUtente(String username, String vecchiaPsw, String nuovaPsw, String nomeUtenteSession) throws SQLException {
        getUtentiDao().updatePswUtente(username, vecchiaPsw, nuovaPsw, nomeUtenteSession);
    }

    //cancellazione utente
    public void CancellaUtente(String username, String psw) throws SQLException {
        getUtentiDao().deleteUtente(username, psw);
    }

}
