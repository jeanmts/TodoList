package org.example.app.todolist.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private Integer status;
    private List<String> errors;
    private String message;
    private LocalDateTime dateTime;
    public ErrorResponse() {
    }

    public ErrorResponse(Integer status,String message, LocalDateTime dateTime, List<String> errors) {
        this.status = status;
        this.errors = errors;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
