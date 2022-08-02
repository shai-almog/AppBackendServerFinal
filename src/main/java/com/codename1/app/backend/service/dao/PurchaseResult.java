package com.codename1.app.backend.service.dao;

public class PurchaseResult {
    private boolean isSuccess;
    private String message;

    public PurchaseResult() {
    }

    public PurchaseResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    /**
     * @return the isSuccess
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
