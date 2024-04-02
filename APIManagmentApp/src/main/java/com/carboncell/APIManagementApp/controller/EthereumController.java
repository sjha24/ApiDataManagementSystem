package com.carboncell.APIManagementApp.controller;

import com.carboncell.APIManagementApp.service.EthereumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@Tag(name="Retrieve Ethereum Account Balance", description="Fetching Ethereum Account Balance Using Address of Ethereum wallet")
public class EthereumController {
    private final EthereumService ethereumService;

    @Autowired
    public EthereumController(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    @Operation(summary = "Fetching Balance")
    @ApiResponse(responseCode = "200", description = "Balance Successfull Retrieve")
    @ApiResponse(responseCode = "401", description = "Not Authorized !!")
    @GetMapping("/balance/{address}")
    public BigDecimal getBalance(@PathVariable String address) {
        try {
            return ethereumService.getAccountBalance(address);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving balance", e);
        }
    }
}
