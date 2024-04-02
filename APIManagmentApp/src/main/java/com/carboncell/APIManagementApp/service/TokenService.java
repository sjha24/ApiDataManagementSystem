package com.carboncell.APIManagementApp.service;

import com.carboncell.APIManagementApp.model.Token;
import com.carboncell.APIManagementApp.model.TokenType;
import com.carboncell.APIManagementApp.model.User;
import com.carboncell.APIManagementApp.repository.IUserRepository;
import com.carboncell.APIManagementApp.repository.TokenRepository;
import com.carboncell.APIManagementApp.security.user.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TokenService implements ITokenService {

    private final TokenRepository tokenRepository;
    private final IUserRepository userRepository;

    @Override
    public void buildTokenDetails(String token, CustomUserDetails userDetail) {

        var getUser = userRepository.findByEmail(userDetail.getEmail()).orElse(null);

        var jwtToken = Token.builder()
                .user(getUser)
                .token(token)
                .tokenType(TokenType.BEARER)
                .isLogout(false)
                .build();

        assert getUser != null;
        revokedAllTokenByUser(getUser);
        tokenRepository.save(jwtToken);
    }

    private void revokedAllTokenByUser(User getUser) {
        List<Token>validTokenListByUser = tokenRepository.findALlTokenByUser(getUser.getUserId());

        if(!validTokenListByUser.isEmpty()){
            validTokenListByUser.forEach(t->t.setLogout(true));
        }
        tokenRepository.saveAll(validTokenListByUser);
    }

}
