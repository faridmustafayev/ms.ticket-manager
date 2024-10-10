package az.ingress.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private UUID id;
    private String customerFullName;
    private Long accountId;
    private Long meetupId;
    private Long paymentId;
    private BigDecimal price;
    private LocalDateTime reserveDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
