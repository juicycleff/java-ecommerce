package io.recruitment.assessment.api.exception;

import java.util.Date;

public class ErrorMessage {

    private String message;

    private int statusCode;

    private Date timestamp;

    private String description;

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
