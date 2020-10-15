package com.lucaspedrosoti.expensetracker.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lucaspedrosoti.expensetracker.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

    String authHeader = httpRequest.getHeader("Authorization");
    if (authHeader != null) {
      String[] authHeaderArray = authHeader.split("Bearer ");
      if (authHeaderArray.length > 1 && authHeaderArray[1] != null) {
        String token = authHeaderArray[1];

        try {
          Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token).getBody();

          httpRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
        } catch (Exception e) {
          httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/expired token");
          return;
        }
      } else {
        httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
        return;
      }
    } else {
      httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
      return;
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
