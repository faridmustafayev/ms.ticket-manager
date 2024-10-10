package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    TICKET_NOT_FOUND_CODE("TICKET_NOT_FOUND"),
    TICKET_NOT_FOUND_MESSAGE("Ticket not found"),
    CLIENT_ERROR_MESSAGE("Exception from Client"),

    UNEXPECTED_ERROR("Unexpected error occurred"),
    INVALID_RESERVATION_TIME("Invalid event date: [%s]. Please ensure it is at least 3 days from today.");

    private final String message;
}