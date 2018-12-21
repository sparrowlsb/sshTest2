package com.world.ico.auth;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hay on 2016/9/19.
 */
public class CrossFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if ("OPTIONS".equals(request.getMethod())) {
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Expose-Headers", "x-requested-with,content-type,CA-Token,Client-Flag");
            response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type,CA-Token,Client-Flag,X_Requested_With");
            //response.setStatus(202);
//            return;
//            response.addHeader("Access-Control-Max-Age", "1800");//30 min
//        }
//        String token = request.getHeader(Constants.CATOKEN_Attribute);
//        if (token != null) {
//            response.setHeader(Constants.CATOKEN_Attribute, token);
//        }
//          String clientflagStr=request.getHeader(Constants.CLIENTFLAG_Attribute);
//        if (clientflagStr!= null) {
//            int clientflag =Integer.valueOf(clientflagStr);
//            response.setHeader(Constants.CLIENTFLAG_Attribute, String.valueOf(clientflag));
//        }

        filterChain.doFilter(request, response);
    }
}