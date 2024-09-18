package it.unicam.cheatBackend.components;

import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CSPComponent implements Filter{
    //non c'è bisogno di inizializzazione, il componente viene eseguito automaticamente allo start

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse risposta = (HttpServletResponse) servletResponse;
        //Content-Security Policy
        risposta.setHeader("Content-Security-Policy",
                "default-src 'self'; script-src 'self'; style-src 'self'; object-src 'none';");
        //default-src 'self' -> le risorse possono essere caricate solo dallo stesso dominio in cui è presente il frontend
        //script-src 'self' -> i JavaScript sono eseguibili solo se provengono dallo stesso dominio in cui è presente il frontend
        //object-src 'none' -> non è permesso caricare oggetti come <object>, <embed>, ecc.
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
