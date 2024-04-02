package com.carboncell.APIManagementApp.service;
import com.carboncell.APIManagementApp.model.APIEntry;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IAPIService {
    List<APIEntry> fetchApis(String Category, Integer limit);
}