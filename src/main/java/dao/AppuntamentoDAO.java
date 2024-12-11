package dao;

import dto.AppuntamentoDTO;
import dto.UtenteDTO;
import utility.DbConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;

public class AppuntamentoDAO {

    private DbConnectionSingleton Dbconnection;
    private ArrayList<AppuntamentoDTO> listaAppuntamenti;

    public void setListaAppuntamenti(ArrayList<AppuntamentoDTO> listaAppuntamenti) {
        this.listaAppuntamenti = listaAppuntamenti;
    }

    public void setDbconnection(DbConnectionSingleton dbconnection) {
        Dbconnection = dbconnection;
    }

    //costrutt
    public AppuntamentoDAO() throws SQLException {
        setDbconnection(DbConnectionSingleton.getInstance());
        setListaAppuntamenti(new ArrayList<>());
    }

    public void AddToListaAppuntamenti(AppuntamentoDTO appObj) {
        getListaAppuntamenti().add(appObj);
    }

    public DbConnectionSingleton getDbconnection() {
        return Dbconnection;
    }

    public ArrayList<AppuntamentoDTO> getListaAppuntamenti() {
        return listaAppuntamenti;
    }

    public ArrayList<AppuntamentoDTO> getAllAppuntamentiDelGiorno(String mese, String anno, String giorno, UtenteDTO user) throws SQLException {

        try {

            Connection conn = getDbconnection().getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT id_utente , data , ora , descrizione" +
                    " FROM appuntamenti" +
                    " where id_utente in " +
                    "(SELECT id from utenti where username = ? ) AND data = TO_DATE(?, 'DD/MM/YYYY')"
            );

            stmt.setString(1, user.getUsername());
            stmt.setString(2, giorno + "/" + mese + "/" + anno);

            stmt.executeQuery();

            ResultSet result = stmt.getResultSet();

            while (result.next()) {
                AppuntamentoDTO appObj = new AppuntamentoDTO();
                appObj.setIdUtente(result.getInt("id_utente"));
                appObj.setData(result.getDate("data"));
                appObj.setOra(result.getTime("ora"));
                appObj.setDescrizione(result.getString("descrizione"));
                AddToListaAppuntamenti(appObj);
            }


        } catch (SQLException ex) {
            throw new SQLException(ex);
        }


        return getListaAppuntamenti();
    }

    public void insertNewAppuntamento(String idUser, DataDAO data, String orarioApp, String descrizioneApp) throws SQLException {
        try {

            Connection conn = getDbconnection().getConnection();

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO appuntamenti (id_utente , data , ora , descrizione) VALUES (?,to_date(? , 'dd/mm/yyyy'),?,?)"
            );

            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();

            stmt.setInt(1, Integer.parseInt(idUser));
            stmt.setString(2, sb.append(data.getGiorno()).append("/").append(data.getMese()).append("/").append(data.getAnno()).toString());
            stmt.setTime(3, Time.valueOf(sb1.append(orarioApp).append(":").append("00").toString()));
            stmt.setString(4, descrizioneApp);


            if (stmt.executeUpdate() != 1) {
                throw new SQLException("insert fallita. Inserimento nuovo appuntamento fallito.");
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void ModificaAppuntamento() throws SQLException {

        try (Connection conn = getDbconnection().getConnection()) {

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void CancellaAppuntamento() throws SQLException {

        try (Connection conn = getDbconnection().getConnection()) {

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
}
