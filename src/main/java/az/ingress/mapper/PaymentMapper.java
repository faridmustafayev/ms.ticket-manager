package az.ingress.mapper;

import az.ingress.model.client.AccountResponse;
import az.ingress.model.client.PaymentRequest;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public PaymentRequest buildPaymentRequest(AccountResponse accountResponse) {
        return PaymentRequest.builder()
                .accountId(accountResponse.getId())
                .balance(accountResponse.getBalance())
                .iban(accountResponse.getIban())
                .build();
    }
}
