package org.fluffytiger.playerinventory.model;

public class TransferResponse {
    public enum Status {
        SUCCESS("Success"),
        NO_SUCH_ITEM("No such item"),
        SAME_PLAYER("Specified ids are the same"),
        UNEXPECTED_ERROR("Unexpected error");

        private final String defaultMessage;

        Status(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }
    }

    public static final TransferResponse SUCCESS_RESPONSE = new TransferResponse(Status.SUCCESS);

    private final Status status;
    private final String message;

    public TransferResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public TransferResponse(Status status) {
        this(status, status.defaultMessage);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
