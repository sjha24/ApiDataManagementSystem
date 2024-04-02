package com.carboncell.APIManagementApp.controller;
import com.carboncell.APIManagementApp.model.APIEntry;
import com.carboncell.APIManagementApp.service.IAPIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="API Management", description="Operations pertaining to managing APIs")
public class APIController {
    @Autowired
    private IAPIService apiService;


    @Operation(summary = "View a list of available APIs")
    @ApiResponse(responseCode = "200", description = "Logout Successfull")
    @ApiResponse(responseCode = "401", description = "Not Authorized !!")
    @GetMapping("/entries")
    public List<APIEntry> getApis(@RequestParam(value = "category", required = false) String category,
                                  @RequestParam(value = "limit", required = false) Integer limit) {
        return apiService.fetchApis(category,limit);
    }
}
