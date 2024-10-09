package az.ingress.mapper

import az.ingress.dao.entity.MeetupEntity
import az.ingress.model.common.PageableResponse
import az.ingress.model.enums.MeetupStatus
import az.ingress.model.request.CreateMeetupRequest
import az.ingress.model.response.MeetupResponse
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.domain.Page
import spock.lang.Specification

import static az.ingress.mapper.MeetupMapper.MEETUP_MAPPER

class MeetupMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildMeetupEntity"() {
        given:
        def meetupRequest = random.nextObject(CreateMeetupRequest)

        when:
        def meetupEntity =  MEETUP_MAPPER.buildMeetupEntity(meetupRequest)

        then:
        meetupEntity.name == meetupRequest.name
        meetupEntity.description == meetupRequest.description
        meetupEntity.price == meetupRequest.price
        meetupEntity.eventDate == meetupRequest.eventDate
    }


    def "TestToMeetupResponse"() {
        given:
        def meetupEntity = random.nextObject(MeetupEntity)

        when:
        def meetupResponse = MEETUP_MAPPER.toMeetupResponse(meetupEntity)

        then:
        meetupResponse.id == meetupEntity.id
        meetupResponse.name == meetupEntity.name
        meetupResponse.description == meetupEntity.description
        meetupResponse.price == meetupEntity.price
        meetupResponse.eventDate == meetupEntity.eventDate
    }


    def "TestSetStatus"() {
        given:
        def meetupEntity = random.nextObject(MeetupEntity)
        def meetupStatus = random.nextObject(MeetupStatus)

        when:
        MEETUP_MAPPER.setStatus(meetupEntity, meetupStatus)

        then:
        meetupEntity.status == meetupStatus
    }


    def "TestBuildPageableResponse"() {
        given:
        def meetupEntityList = random.nextObject(Page<MeetupEntity>)

        def pageableResponse = random.nextObject(PageableResponse<MeetupResponse>)
        def meetupResponse = random.nextObject(MeetupResponse)
        pageableResponse.content = [meetupResponse]

        when:
        MEETUP_MAPPER.buildPageableResponse(meetupEntityList) >> pageableResponse

        then:
        pageableResponse.content == meetupEntityList.content
        pageableResponse.hasNextPage == meetupEntityList.hasNext()
        pageableResponse.totalElements == meetupEntityList.totalElements
        pageableResponse.totalPages == meetupEntityList.totalPages
    }

}
