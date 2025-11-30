package com.ajhar.productservicemanagement.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private String path;
    private List<String> errors; // optional, for validation errors

    public ErrorResponse() {}

    public ErrorResponse(String message, LocalDateTime timestamp, String path) {
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    public ErrorResponse(String message, LocalDateTime timestamp, String path, List<String> errors) {
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
        this.errors = errors;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}
