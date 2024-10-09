package az.ingress.client.mock;

import az.ingress.client.AccountClient;
import az.ingress.model.client.AccountResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Profile("local")
public class MockAccountClient implements AccountClient {

    @Override
    public AccountResponse getAccount(Long accountId) {
        return AccountResponse.builder()
                .id(accountId)
                .balance(BigDecimal.valueOf(1000.50))
                .iban("DE23A5902D1209H4578L21K34567")
                .createdAt(LocalDateTime.now().minusYears(1))
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
