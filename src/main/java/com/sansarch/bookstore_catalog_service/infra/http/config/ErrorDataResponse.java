package com.sansarch.bookstore_catalog_service.infra.http.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDataResponse {
    private int status;
    private String detail;
}
