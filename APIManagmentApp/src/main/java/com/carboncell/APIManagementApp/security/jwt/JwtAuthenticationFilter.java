package com.carboncell.APIManagementApp.security.jwt;

import com.carboncell.APIManagementApp.repository.TokenRepository;
import com.carboncell.APIManagementApp.security.user.CustomUserDetailsService;
import com.carboncell.APIManagementApp.service.ITokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private  JwtHelper jwtHelper;

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Authorization = Bearer token
        String requestHeader = request.getHeader("Authorization");

        logger.info("Header :{}",requestHeader);
        String userName = null;
        String token = null;

        if(requestHeader != null && requestHeader.startsWith("Bearer")){
            token = requestHeader.substring(7);

            try {
                userName = jwtHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Illegal argument while fetching username");
            }catch (ExpiredJwtException e){
                logger.info("Given jwt token is expired !!");
            }catch (MalformedJwtException e){
                logger.info("Some change has done in token !! Invalid token");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info("Invalid Header Value !!");
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null){
            //fetch userDetail from username
            UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);
            Boolean validateToken = jwtHelper.validateToken(token,userDetails);

            if(validateToken){
                //set the authentication
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else{
                logger.info("Validation fails !!!");
            }
        }
        filterChain.doFilter(request,response);
    }

}
