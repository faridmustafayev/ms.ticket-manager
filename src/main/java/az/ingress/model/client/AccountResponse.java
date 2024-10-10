package az.ingress.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    private BigDecimal balance;
    private String iban;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
