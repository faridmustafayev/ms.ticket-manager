package az.ingress.client.decoder;

import az.ingress.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static az.ingress.client.decoder.JsonNodeFieldName.MESSAGE;
import static az.ingress.exception.ErrorMessage.CLIENT_ERROR_MESSAGE;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        var errorMessage = CLIENT_ERROR_MESSAGE.toString();

        log.error("ActionLog.decode.error Message: {}, Method: {}", errorMessage, methodKey);

        JsonNode jsonNode;
        try(var body = response.body().asInputStream()) {
            jsonNode = new ObjectMapper().readValue(body, JsonNode.class);
        }catch (Exception ex) {
            throw new CustomFeignException(errorMessage, response.status());
        }

        if (jsonNode.has(MESSAGE.getValue())) {
            errorMessage = jsonNode.get(MESSAGE.getValue()).asText();
        }

        return new CustomFeignException(errorMessage, response.status());
    }
}
