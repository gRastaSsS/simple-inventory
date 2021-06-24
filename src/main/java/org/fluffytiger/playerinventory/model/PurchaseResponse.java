package org.fluffytiger.playerinventory.model;

public class PurchaseResponse {
    public enum Status {
        SUCCESS("Success"),
        INSUFFICIENT_FUNDS("Insufficient funds"),
        UNEXPECTED_ERROR("Unexpected error");

        private final String defaultMessage;

        Status(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }
    }

    public static final PurchaseResponse SUCCESS_RESPONSE = new PurchaseResponse(PurchaseResponse.Status.SUCCESS);

    private final Status status;
    private final String message;

    public PurchaseResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public PurchaseResponse(Status status) {
        this(status, status.defaultMessage);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
