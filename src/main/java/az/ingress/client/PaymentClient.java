package az.ingress.client;

import az.ingress.client.decoder.CustomErrorDecoder;
import az.ingress.model.client.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-payment",
        url = "${client.urls.ms-payment}",
        configuration = CustomErrorDecoder.class)
public interface PaymentClient {

    @PostMapping("internal/v1/payments")
    void processPayment(@RequestBody PaymentRequest request);

}
