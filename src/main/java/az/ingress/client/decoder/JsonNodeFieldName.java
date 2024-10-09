package az.ingress.client.decoder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JsonNodeFieldName {
    MESSAGE("message");
    private final String value;
}
