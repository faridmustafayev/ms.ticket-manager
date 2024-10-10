package az.ingress.controller

import az.ingress.exception.ErrorHandler
import az.ingress.model.common.PageableResponse
import az.ingress.model.criteria.MeetupCriteria
import az.ingress.model.criteria.PageCriteria
import az.ingress.model.request.CreateMeetupRequest
import az.ingress.model.response.MeetupResponse
import az.ingress.service.abstraction.MeetupService
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static io.github.benas.randombeans.EnhancedRandomBuilder.aNewEnhancedRandom
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class MeetupControllerTest extends Specification {
    EnhancedRandom random = aNewEnhancedRandom()
    MeetupService meetupService
    MeetupController meetupController
    MockMvc mockMvc

    def setup() {
        meetupService = Mock()
        meetupController = new MeetupController(meetupService)
        mockMvc = standaloneSetup(meetupController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestCreateMeetup: success case"() {
        given:
        def url = "/v1/meetups"

        def meetupRequest = random.nextObject(CreateMeetupRequest)

        def jsonRequest = """
                                {
                                  "name": "$meetupRequest.name",
                                  "description": "$meetupRequest.description",
                                  "price": $meetupRequest.price,
                                  "eventDate": ${
                                                [meetupRequest.eventDate.year,
                                                 meetupRequest.eventDate.monthValue,
                                                 meetupRequest.eventDate.dayOfMonth,
                                                 meetupRequest.eventDate.hour,
                                                 meetupRequest.eventDate.minute,
                                                 meetupRequest.eventDate.second]}
                                 }
                                """

        when:
        def actual = mockMvc.perform(post(url)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(jsonRequest)
        )

        then:
        1 * meetupService.createMeetup(meetupRequest)

        actual.andExpect(
                status().isCreated()
        )
    }

    def "TestGetAllMeetups: success case"() {
        given:
        def url = "/v1/meetups"

        def pageCriteria = random.nextObject(PageCriteria)
        def meetupCriteria = random.nextObject(MeetupCriteria)

        def pageableResponse = random.nextObject(PageableResponse, "content")
        def meetupResponse = random.nextObject(MeetupResponse)
        pageableResponse.content = [meetupResponse]

        def jsonResponse = """
                                {
                                  "content": [
                                            {
                                              "id": "$meetupResponse.id",
                                              "name": "$meetupResponse.name",
                                              "description": "$meetupResponse.description",
                                              "price": $meetupResponse.price,
                                              "eventDate": ${
                                                            [meetupResponse.eventDate.year,
                                                             meetupResponse.eventDate.monthValue,
                                                             meetupResponse.eventDate.dayOfMonth,
                                                             meetupResponse.eventDate.hour,
                                                             meetupResponse.eventDate.minute,
                                                             meetupResponse.eventDate.second]}
                                            }
                                  ],
                                  "totalElements": $pageableResponse.totalElements,
                                  "totalPages": $pageableResponse.totalPages,
                                  "hasNextPage": $pageableResponse.hasNextPage
                                }
                                """

        when:
        def actual = mockMvc.perform(get(url)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .param("page", pageCriteria.page as String)
                .param("size", pageCriteria.size as String)
                .param("name", meetupCriteria.name)
        )

        then:
        1 * meetupService.getAllMeetups(meetupCriteria, pageCriteria) >> pageableResponse

        actual.andExpectAll(
                status().isOk(),
                content().json(jsonResponse)
        )
    }
}