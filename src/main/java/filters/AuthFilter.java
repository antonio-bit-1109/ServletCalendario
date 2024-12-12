package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filtro che protegge le risorse riservate
 * e reindirizza gli utenti non autenticati.
 */
@WebFilter("/protected/*") // Protegge tutte le risorse sotto il path /restricted/*
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione opzionale
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Controlla se l'utente è loggato (esistenza di un attributo nella sessione)
        if (httpRequest.getSession().getAttribute("user") == null) {
            // Se non loggato, reindirizza alla pagina di login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
        } else {
            // Se loggato, passa la richiesta alla risorsa successiva
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup opzionale
    }
}