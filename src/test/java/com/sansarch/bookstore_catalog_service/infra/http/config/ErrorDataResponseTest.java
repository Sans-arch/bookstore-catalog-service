package com.sansarch.bookstore_catalog_service.infra.http.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDataResponseTest {

    @Test
    void shouldCreateErrorDataResponse() {
        ErrorDataResponse errorDataResponse = new ErrorDataResponse(404, "Not Found");
        assertEquals(404, errorDataResponse.getStatus());
        assertEquals("Not Found", errorDataResponse.getDetail());
    }

    @Test
    void shouldSetErrorDataResponse() {
        ErrorDataResponse errorDataResponse = new ErrorDataResponse(404, "Not Found");
        errorDataResponse.setStatus(500);
        errorDataResponse.setDetail("Internal Server Error");
        assertEquals(500, errorDataResponse.getStatus());
        assertEquals("Internal Server Error", errorDataResponse.getDetail());
    }

    @Test
    void shouldReturnErrorDataResponseAsString() {
        ErrorDataResponse errorDataResponse = new ErrorDataResponse(404, "Not Found");
        assertEquals("ErrorDataResponse(status=404, detail=Not Found)", errorDataResponse.toString());
    }
}