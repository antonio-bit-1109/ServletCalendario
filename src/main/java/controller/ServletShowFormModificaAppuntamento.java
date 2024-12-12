package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowFormModificaAppuntamento")
public class ServletShowFormModificaAppuntamento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // prendo parametri dall url
        String mese = req.getParameter("mese");
        String anno = req.getParameter("anno");
        String giorno = req.getParameter("giorno");
        String idAppuntamento = req.getParameter("idApp");

        // li setto nella request
        req.setAttribute("mese", mese);
        req.setAttribute("anno", anno);
        req.setAttribute("giorno", giorno);
        req.setAttribute("idApp", idAppuntamento);

        // li reinvio alla jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("/protected/FormModificaAppuntamento.jsp");
        dispatcher.forward(req, resp);
    }
}
