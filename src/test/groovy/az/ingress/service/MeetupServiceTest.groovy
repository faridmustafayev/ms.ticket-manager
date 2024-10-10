package az.ingress.service

import az.ingress.dao.repository.MeetupRepository
import az.ingress.model.common.PageableResponse
import az.ingress.model.criteria.MeetupCriteria
import az.ingress.model.criteria.PageCriteria
import az.ingress.model.request.CreateMeetupRequest
import az.ingress.model.response.MeetupResponse
import az.ingress.service.abstraction.MeetupService
import az.ingress.service.concrete.MeetupServiceHandler
import az.ingress.service.specification.MeetupSpecification
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

import static az.ingress.mapper.MeetupMapper.MEETUP_MAPPER
import static az.ingress.util.MeetupValidationUtil.MEETUP_VALIDATION_UTIL

class MeetupServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    MeetupRepository meetupRepository
    MeetupService meetupService

    def setup() {
        meetupRepository = Mock()
        meetupService = new MeetupServiceHandler(meetupRepository)
    }

    def "TestCreateMeetup"() {
        given:
        def meetupRequest = random.nextObject(CreateMeetupRequest)
        def meetupEntity = MEETUP_MAPPER.buildMeetupEntity(meetupRequest)
        MEETUP_VALIDATION_UTIL.ensureEventDateIsAcceptable(meetupRequest.getEventDate())

        when:
        meetupService.createMeetup(meetupRequest)

        then:
        1 * meetupRepository.save(meetupEntity)
    }


    def "TestGetAllMeetups"() {
        given:
        def meetupCriteria = random.nextObject(MeetupCriteria)
        def pageCriteria = random.nextObject(PageCriteria)

        def specification = new MeetupSpecification(meetupCriteria)
        def pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize())

        def pageableResponse = random.nextObject(PageableResponse)
        def meetupResponse = random.nextObject(MeetupResponse)
        pageableResponse.content = [meetupResponse]

        when:
        meetupService.getAllMeetups(meetupCriteria, pageCriteria) >> pageableResponse

        then:
        1 * meetupRepository.findAll(specification, pageable)
    }

}
