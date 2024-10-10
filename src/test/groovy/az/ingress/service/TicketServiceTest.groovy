package az.ingress.service

import az.ingress.client.mock.MockAccountClient
import az.ingress.client.mock.MockPaymentClient
import az.ingress.dao.entity.TicketEntity
import az.ingress.dao.repository.TicketRepository
import az.ingress.mapper.PaymentMapper
import az.ingress.mapper.TicketMapper
import az.ingress.model.request.TicketRequest
import az.ingress.service.abstraction.TicketService
import az.ingress.service.concrete.TicketServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import java.util.stream.Collectors

import static az.ingress.mapper.TicketMapper.TICKET_MAPPER
import static az.ingress.mapper.TicketMapper.TICKET_MAPPER

class TicketServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    TicketRepository ticketRepository
    MockAccountClient accountClient
    MockPaymentClient paymentClient
    TicketService ticketService

    def setup() {
        ticketRepository = Mock()
        accountClient = Mock()
        paymentClient = Mock()
        ticketService = new TicketServiceHandler(ticketRepository, accountClient, paymentClient)
    }

    def "TestSaveTicket"() {
        given:
        def ticketRequest = random.nextObject(TicketRequest)
        def accountResponse = accountClient.getAccount(ticketRequest.getAccountId())
        paymentClient.processPayment(PaymentMapper.PAYMENT_MAPPER.buildPaymentRequest(accountResponse))
        def ticketEntity = TICKET_MAPPER.buildTicketEntity(ticketRequest)

        when:
        ticketService.saveTicket(ticketRequest)

        then:
        1 * ticketRepository.save(ticketEntity)
    }


    def "TestGetTicketsByAccountId success case"() {
        given:
        def accountId = random.nextObject(Long)
        def ticketEntity = random.nextObject(TicketEntity)
        def expected = List.of(ticketEntity).stream()
                .map(TICKET_MAPPER::buildTicketResponse)
                .collect(Collectors.toList())

        when:
        def actual = ticketService.getTicketsByAccountId(accountId)

        then:
        1 * ticketRepository.findTicketsByAccountId(accountId) >> expected
        actual == expected
    }

}
