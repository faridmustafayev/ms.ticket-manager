package az.ingress.service.concrete;

import az.ingress.dao.repository.MeetupRepository;
import az.ingress.model.common.PageableResponse;
import az.ingress.model.criteria.MeetupCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateMeetupRequest;
import az.ingress.model.response.MeetupResponse;
import az.ingress.service.abstraction.MeetupService;
import az.ingress.service.specification.MeetupSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static az.ingress.mapper.MeetupMapper.MEETUP_MAPPER;
import static az.ingress.model.constants.MeetupConstants.EXPIRATION_THRESHOLD_HOURS;
import static az.ingress.model.enums.MeetupStatus.ACTIVE;
import static az.ingress.model.enums.MeetupStatus.CLOSED;
import static az.ingress.util.MeetupValidationUtil.MEETUP_VALIDATION_UTIL;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class MeetupServiceHandler implements MeetupService {
    private final MeetupRepository meetupRepository;

    public void createMeetup(CreateMeetupRequest meetupRequest) {
        MEETUP_VALIDATION_UTIL.ensureEventDateIsAcceptable(meetupRequest.getEventDate());
        var meetupEntity = MEETUP_MAPPER.buildMeetupEntity(meetupRequest);
        meetupRepository.save(meetupEntity);
    }

    public PageableResponse<MeetupResponse> getAllMeetups(MeetupCriteria meetupCriteria, PageCriteria pageCriteria) {
        var specification = new MeetupSpecification(meetupCriteria);
        var pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize());
        var meetups = meetupRepository.findAll(specification, pageable);
        return MEETUP_MAPPER.buildPageableResponse(meetups);
    }

    @Transactional
    public void updateExpiredMeetupStatus() {
        var expirationThresholdTime = now().minusHours(EXPIRATION_THRESHOLD_HOURS);
        var meetups = meetupRepository.findAllByEventDateBeforeAndStatus(expirationThresholdTime, ACTIVE);
        for (var meetup : meetups) {
            MEETUP_MAPPER.setStatus(meetup, CLOSED);
        }
    }
}