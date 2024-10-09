package az.ingress.service.abstraction;

import az.ingress.model.common.PageableResponse;
import az.ingress.model.criteria.MeetupCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateMeetupRequest;
import az.ingress.model.response.MeetupResponse;

public interface MeetupService {

    void createMeetup(CreateMeetupRequest meetupRequest);

    PageableResponse<MeetupResponse> getAllMeetups(MeetupCriteria meetupCriteria, PageCriteria pageCriteria);

    void updateExpiredMeetupStatus();
}