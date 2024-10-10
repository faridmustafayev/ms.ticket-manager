package az.ingress.mapper

import az.ingress.model.client.AccountResponse
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static az.ingress.mapper.PaymentMapper.PAYMENT_MAPPER

class PaymentMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildPaymentRequest"() {
        given:
        def accountResponse = random.nextObject(AccountResponse)

        when:
        def paymentRequest = PAYMENT_MAPPER.buildPaymentRequest(accountResponse)

        then:
        paymentRequest.balance == accountResponse.balance
        paymentRequest.iban == accountResponse.iban
    }

}
