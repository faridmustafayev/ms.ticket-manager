package az.ingress.mapper

import az.ingress.dao.entity.TicketEntity
import az.ingress.model.request.TicketRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static az.ingress.mapper.TicketMapper.TICKET_MAPPER

class TicketMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildTicketEntity"() {
        given:
        def ticketRequest = random.nextObject(TicketRequest)

        when:
        def ticketEntity = TICKET_MAPPER.buildTicketEntity(ticketRequest)

        then:
        ticketEntity.accountId == ticketRequest.accountId
        ticketEntity.customerFullName == ticketRequest.customerFullName
        ticketEntity.meetupId == ticketRequest.meetupId
        ticketEntity.paymentId == ticketRequest.paymentId
        ticketEntity.price == ticketRequest.price
        ticketEntity.reserveDate == ticketRequest.reserveDate
    }


    def "TestBuildTicketResponse"() {
        given:
        def ticketEntity = random.nextObject(TicketEntity)

        when:
        def ticketResponse = TICKET_MAPPER.buildTicketResponse(ticketEntity)

        then:
        ticketResponse.id == ticketEntity.id
        ticketResponse.customerFullName == ticketEntity.customerFullName
        ticketResponse.accountId == ticketEntity.accountId
        ticketResponse.meetupId == ticketEntity.meetupId
        ticketResponse.paymentId == ticketEntity.paymentId
        ticketResponse.price == ticketEntity.price
        ticketResponse.reserveDate == ticketEntity.reserveDate
        ticketResponse.createdAt == ticketEntity.createdAt
        ticketResponse.updatedAt == ticketEntity.updatedAt
    }

}
