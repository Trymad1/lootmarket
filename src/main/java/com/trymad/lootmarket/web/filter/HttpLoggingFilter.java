package com.trymad.lootmarket.web.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component
public class HttpLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse reponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURL().toString();
        String method = httpRequest.getMethod();

        log.debug("Request URL: {}, HTTP Method: {}", url, method);

        chain.doFilter(request, reponse);
    }

}
