package az.ingress.client.mock;

import az.ingress.client.PaymentClient;
import az.ingress.model.client.PaymentRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("local")
public class MockPaymentClient implements PaymentClient {

    @Override
    public void processPayment(PaymentRequest request) {
        PaymentRequest mockRequest = new PaymentRequest(
                1L,
                BigDecimal.valueOf(500.00),
                "DE89 3704 0044 0532 0130 00"
        );

        System.out.println("Mock payment processed for account ID: " + mockRequest.getAccountId());
        System.out.println("Balance: " + mockRequest.getBalance());
        System.out.println("IBAN: " + mockRequest.getIban());
    }
}
