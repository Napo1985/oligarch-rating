package org.example.oligarchrating.web.exceptionHandler;

import org.example.oligarchrating.domain.exception.DuplicationException;
import org.example.oligarchrating.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleDuplicationException() {
        DuplicationException ex = new DuplicationException("Resource already exists");
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleDuplicationException(ex);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Resource already exists", responseEntity.getBody());
    }

    @Test
    void handleNotFoundException() {
        NotFoundException ex = new NotFoundException("Resource not found");
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleNotFoundException(ex);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Resource not found", responseEntity.getBody());
    }

    @Test
    void handleRuntimeException() {
        RuntimeException ex = new RuntimeException("Internal server error");
        ResponseEntity<String> responseEntity = globalExceptionHandler.handleRuntimeException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error", responseEntity.getBody());
    }
}