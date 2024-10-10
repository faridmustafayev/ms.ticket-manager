package az.ingress.client;

import az.ingress.client.decoder.CustomErrorDecoder;
import az.ingress.model.client.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-account",
        url = "${clients.urls.ms-account}",
        configuration = CustomErrorDecoder.class)
public interface AccountClient {

    @GetMapping("internal/v1/accounts/{accountId}")
    AccountResponse getAccount(@PathVariable Long accountId);
}
