package com.carboncell.APIManagementApp.DTO;

import com.carboncell.APIManagementApp.model.APIEntry;
import lombok.Data;

import java.util.List;
@Data
public class APIResponse {
    private int count;
    private List<APIEntry> entries;
}
