package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String customerFullName;
    private Long accountId;
    private Long meetupId;
    private Long paymentId;
    private BigDecimal price;
    private LocalDateTime reserveDate;
}
