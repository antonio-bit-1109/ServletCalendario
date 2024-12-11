package dao;

import dto.UtenteDTO;
import utility.DbConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


// effettuo tutte le crud relative all utente
// readUtente
// create utente
// update utente
// delete utente
public class UtentiDAO {

    private DbConnectionSingleton Dbconnection;

    public void setDbconnection(DbConnectionSingleton dbconnection) {
        Dbconnection = dbconnection;
    }

    //costrutt
    public UtentiDAO() throws SQLException {
        setDbconnection(DbConnectionSingleton.getInstance());
    }

    public DbConnectionSingleton getDbconnection() {
        return Dbconnection;
    }

    // read utente
    public UtenteDTO GetUtente(String username) throws SQLException {

        UtenteDTO user;
        try {

            Connection conn = getDbconnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT id , username , password from utenti where username = ?");
            stmt.setString(1, username);

            stmt.executeQuery();

            ResultSet result = stmt.getResultSet();

            user = new UtenteDTO();

            while (result.next()) {
                user.setId(result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
            }
            return user;
        } catch (SQLException ex) {
            throw new SQLException("errore durante la get dell utente." + ex);
        }

    }

    // get all users
    public ArrayList<UtenteDTO> getAllUsers() throws SQLException {

        try (Connection conn = getDbconnection().getConnection()) {

            PreparedStatement stmt = conn.prepareStatement("SELECT username , password from utenti");

            ResultSet result = stmt.executeQuery();

            ArrayList<UtenteDTO> listaUtenti = new ArrayList<>();

            while (result.next()) {
                UtenteDTO utente = new UtenteDTO();
                utente.setUsername(result.getString("username"));
                utente.setPassword(result.getString("password"));
                listaUtenti.add(utente);
            }

            return listaUtenti;

        } catch (SQLException e) {
            throw new SQLException("errore durante la get di tutti gli utenti" + e);
        }
    }


    // create utente
    public int InsertNewUtente(String username, String psw) throws SQLException {
        try (Connection conn = getDbconnection().getConnection()) {


            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO utenti (username , password) values (? , ?)"
            );
            stmt.setString(1, username);
            stmt.setString(2, psw);


            return stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException("errore durante la insert dell utente." + ex);
        }
    }

    // update utente
    public void updatePswUtente(String username, String Password, String newPassword, String nomeUtenteSession) throws SQLException {
        Connection conn = getDbconnection().getConnection();
        try {

            conn.setAutoCommit(false);

            conn = getDbconnection().getConnection();


            if (!username.equals(nomeUtenteSession)) {
                conn.rollback();
                throw new RuntimeException("il nome dell utente registrato in sessione è diverso da quello atteso.");
            }

            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE utenti SET password = ? where username = ?"
            );
            stmt.setString(1, newPassword);
            stmt.setString(2, username);

            if (stmt.executeUpdate() != 1) {
                conn.rollback();
                throw new SQLException("Err: utente non inserito");
            }

            //return stmt.executeUpdate();
            if (stmt.executeUpdate() != 1) {
                throw new RuntimeException("le righe ritornate non sono quelle previste.");
            }
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw new SQLException("errore durante la insert dell utente." + ex);
        }
    }

    // delete utente
    public void deleteUtente(String username, String psw) throws SQLException {
        try (Connection conn = getDbconnection().getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM utenti where username = ? and password = ?"
            );
            stmt.setString(1, username);
            stmt.setString(2, psw);
            //stmt.setString(1, username);

            if (stmt.executeUpdate() != 1) {
                throw new RuntimeException("utente cancellato, valore inatteso.");
            }
        } catch (SQLException ex) {
            throw new SQLException("errore durante la insert dell utente." + ex);
        }

    }
}
