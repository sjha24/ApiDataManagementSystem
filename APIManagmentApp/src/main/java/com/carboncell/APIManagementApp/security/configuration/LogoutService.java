package com.carboncell.APIManagementApp.security.configuration;

import com.carboncell.APIManagementApp.model.Token;
import com.carboncell.APIManagementApp.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }

        String jwtToken = authHeader.substring(7);

        Token olderToken = tokenRepository.findByToken(jwtToken).orElse(null);

        if(olderToken != null){
            olderToken.setLogout(true);
            tokenRepository.save(olderToken);
        }

    }


}
