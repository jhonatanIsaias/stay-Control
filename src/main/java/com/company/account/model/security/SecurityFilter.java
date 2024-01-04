package com.company.account.model.security;

import com.company.account.model.repositories.CompanyRepository;
import com.company.account.model.services.CompanyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    CompanyRepository companyRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(token != null){
            var subject = tokenService.validateToken(token);
            System.out.println(subject);
            UserDetails company = companyRepository.findByEmail(subject);


            var authentication = new UsernamePasswordAuthenticationToken(company,null,company.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        System.out.println("vou para o proximo filtro");
        filterChain.doFilter(request,response);
    }
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return  null;
        return authHeader.replace("Bearer ","");
    }

}
