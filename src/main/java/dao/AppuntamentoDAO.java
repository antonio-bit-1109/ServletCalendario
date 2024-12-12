package dao;

import dto.AppuntamentoDTO;
import dto.UtenteDTO;
import utility.DbConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

            PreparedStatement stmt = conn.prepareStatement("SELECT id , id_utente , data , ora , descrizione" +
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
                appObj.setId(result.getInt("id"));
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
                    "INSERT INTO appuntamenti (id_utente , data , ora , descrizione)" +
                            " VALUES (?,to_date(? , 'dd/mm/yyyy'),?,?)"
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

    public void ModificaAppuntamentoDb(int idApp, String giorno, String mese, String anno, String nuovaOra, String nuovaDesc) throws SQLException {

        try {
            Connection conn = getDbconnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE appuntamenti " +
                    " SET data = to_date( ? , 'dd/mm/yyyy') ," +
                    " ora = ? ," +
                    " descrizione = ?" +
                    " where id = ? ");

            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();

            stmt.setString(1, sb.append(giorno).append("/").append(mese).append("/").append(anno).toString());
            stmt.setTime(2, Time.valueOf(sb1.append(nuovaOra).append(":").append("00").toString()));
            stmt.setString(3, nuovaDesc);
            stmt.setInt(4, idApp);

            if (stmt.executeUpdate() != 1) {
                throw new SQLException("Errore durante la modifica dell'appuntamento.");
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void DeleteAppuntamentoDb(int idAppuntamento) throws SQLException {

        try {
            Connection conn = getDbconnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM appuntamenti where id = ?");
            stmt.setInt(1, idAppuntamento);

            if (stmt.executeUpdate() != 1) {
                throw new SQLException("errore durante la cancellazione dell'appuntamento.");
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public ArrayList<String> GetAppuntamentiDelMese(String anno, String mese) throws SQLException {
        try {
            Connection conn = getDbconnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT  extract (day from ap.data) as qtaImpegni" +
                    " FROM appuntamenti ap" +
                    " WHERE extract (YEAR from ap.data) = ? AND extract (MONTH from ap.data) = ? " +
                    " order by extract (day from ap.data) asc");

            stmt.setInt(1, Integer.parseInt(anno));
            stmt.setInt(2, Integer.parseInt(mese));

            ResultSet res = stmt.executeQuery();

            ArrayList<String> listaImpegni = new ArrayList<>();

            while (res.next()) {
                String val = res.getString("qtaImpegni");
                listaImpegni.add(val);
            }

            return listaImpegni;

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
}
