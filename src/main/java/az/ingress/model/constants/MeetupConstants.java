package az.ingress.model.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class MeetupConstants {
    public static final Integer RESERVATION_THRESHOLD_DAYS = 3;
    public static final Integer EXPIRATION_THRESHOLD_HOURS = 2;
}