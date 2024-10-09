package az.ingress.controller

import az.ingress.exception.ErrorHandler
import az.ingress.model.request.TicketRequest
import az.ingress.model.response.TicketResponse
import az.ingress.service.abstraction.TicketService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TicketControllerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom();
    TicketService ticketService
    TicketController ticketController
    MockMvc mockMvc

    def setup() {
        ticketService = Mock()
        ticketController = new TicketController(ticketService)
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestReserveTicket: success case"() {
        given:
        def url = "/v1/tickets"

        def ticketRequest = random.nextObject(TicketRequest)

        def jsonRequest = """
                {
                    "customerFullName": "$ticketRequest.customerFullName"
                    "accountId": "$ticketRequest.accountId"
                    "meetupId": "$ticketRequest.meetupId"
                    "paymentId": "$ticketRequest.paymentId"
                    "price": "$ticketRequest.price"
                    "reserveDate": ${
            [ticketRequest.reserveDate.year,
             ticketRequest.reserveDate.monthValue,
             ticketRequest.reserveDate.dayOfMonth,
             ticketRequest.reserveDate.hour,
             ticketRequest.reserveDate.minute,
             ticketRequest.reserveDate.second]}
                }
        """

        when:
        def actual = mockMvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )

        then:
        1 * ticketService.saveTicket(ticketRequest)

        actual.andExpect(
                status().isCreated()
        )

    }


    def "TestGetAllTicketsByAccountId: success case"() {
        given:
        def id = random.nextObject(Long)
        def url = "/v1/tickets"

        def ticketResponse = random.nextObject(TicketResponse)

        def jsonResponse = """
                                {
                                    "id": "$ticketResponse.id",
                                    "customerFullName": "$ticketResponse.customerFullName",
                                    "accountId": "$ticketResponse.accountId",
                                    "meetupId": "$ticketResponse.meetupId",
                                    "paymentId": "$ticketResponse.paymentId",
                                    "price": "$ticketResponse.price",
                                    "reserveDate": "$ticketResponse.reserveDate",
                                    "createdAt": ${
                                                    [ticketResponse.createdAt.year,
                                                     ticketResponse.createdAt.monthValue,
                                                     ticketResponse.createdAt.dayOfMonth,
                                                     ticketResponse.createdAt.hour,
                                                     ticketResponse.createdAt.minute,
                                                     ticketResponse.createdAt.second]},
                                    "updatedAt": ${
                                                    [ticketResponse.updatedAt.year,
                                                     ticketResponse.updatedAt.monthValue,
                                                     ticketResponse.updatedAt.dayOfMonth,
                                                     ticketResponse.updatedAt.hour,
                                                     ticketResponse.updatedAt.minute,
                                                     ticketResponse.updatedAt.second]}
                                }
        """

        when:
        def actual = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("accountId", ticketResponse.accountId as String)
        )


        then:
        1 * ticketService.getTicketsByAccountId(id) >> ticketResponse

        actual.andExpectAll(
                status().isOk(),
                content().json(jsonResponse)
        )

    }

}
