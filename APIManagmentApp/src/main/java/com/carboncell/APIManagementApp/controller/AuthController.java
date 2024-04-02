package com.carboncell.APIManagementApp.controller;
import com.carboncell.APIManagementApp.DTO.LoginRequest;
import com.carboncell.APIManagementApp.DTO.Response;
import com.carboncell.APIManagementApp.security.jwt.JwtHelper;
import com.carboncell.APIManagementApp.security.user.CustomUserDetails;
import com.carboncell.APIManagementApp.security.user.CustomUserDetailsService;
import com.carboncell.APIManagementApp.service.ITokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Tag(name="Auth Management", description="managing User Login")
@AllArgsConstructor
public class AuthController {

    private final JwtHelper helper;

    private final CustomUserDetailsService userDetailsService;

    private final AuthenticationManager manager;

    private final ITokenService tokenService;

//    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Operation(summary = "Register User Login")
    @ApiResponse(responseCode = "200", description = "Login Successfully")
    @ApiResponse(responseCode = "401", description = "Not Authorized !!")
    @ApiResponse(responseCode = "208", description = "Your are already Login plz logout first than try to login !!")
    @PostMapping("/login")
    public ResponseEntity<?>authenticateUser(@Parameter(name = "User Login",description = "Enter Your Registered Email and Password") @RequestBody LoginRequest loginRequest){

        this.doAuthenticate(loginRequest.getEmail(),loginRequest.getPassword());
        CustomUserDetails userDetail = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.getEmail());


            String token = this.helper.generateToken(userDetail);

            tokenService.buildTokenDetails(token,userDetail);

            Response response = Response.builder()
                    .token(token)
                    .email(userDetail.getUsername()).build();

            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        try {
            manager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            throw new AuthenticationException("Invalid User name or password") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }


    @Operation(summary = "Register User Logout")
    @ApiResponse(responseCode = "200", description = "Logout Successfull")
    @ApiResponse(responseCode = "401", description = "Not Authorized !!")
    @GetMapping("/logout")
    public ResponseEntity<String>LogoutUser(){
        return new ResponseEntity<>("User logged out successfully",HttpStatus.ACCEPTED);
    }
}
