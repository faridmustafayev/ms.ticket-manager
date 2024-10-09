package az.ingress.model.request;

import az.ingress.model.client.PaymentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static az.ingress.model.constants.DateTimeConstants.DATE_TIME_PATTERN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeetupRequest {
    private String name;

    private String description;

    private BigDecimal price;

    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime eventDate;
}