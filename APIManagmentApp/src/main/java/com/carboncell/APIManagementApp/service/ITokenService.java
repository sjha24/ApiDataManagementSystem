package com.carboncell.APIManagementApp.service;

import com.carboncell.APIManagementApp.security.user.CustomUserDetails;
import org.springframework.stereotype.Service;

@Service
public interface ITokenService {

    void buildTokenDetails(String token, CustomUserDetails userDetail);
}
