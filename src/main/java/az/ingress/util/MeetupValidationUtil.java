package az.ingress.util;

import az.ingress.exception.ValidationException;

import java.time.LocalDateTime;

import static az.ingress.exception.ErrorMessage.INVALID_RESERVATION_TIME;
import static az.ingress.model.constants.MeetupConstants.RESERVATION_THRESHOLD_DAYS;
import static java.time.LocalDateTime.now;

public enum MeetupValidationUtil {
    MEETUP_VALIDATION_UTIL;

    public void ensureEventDateIsAcceptable(LocalDateTime eventDate) {
        var validEventDate = now().plusDays(RESERVATION_THRESHOLD_DAYS);

        if (validEventDate.isAfter(eventDate))
            throw new ValidationException(INVALID_RESERVATION_TIME.getMessage(), eventDate);
    }
}