package az.ingress.controller;

import az.ingress.model.common.PageableResponse;
import az.ingress.model.criteria.MeetupCriteria;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateMeetupRequest;
import az.ingress.model.response.MeetupResponse;
import az.ingress.service.abstraction.MeetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/meetups")
@RequiredArgsConstructor
public class MeetupController {
    private final MeetupService meetupService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createMeetup(@RequestBody CreateMeetupRequest meetupRequest) {
        meetupService.createMeetup(meetupRequest);
    }

    @GetMapping
    public PageableResponse<MeetupResponse> getAllMeetups(MeetupCriteria meetupCriteria,
                                                          PageCriteria pageCriteria) {
        return meetupService.getAllMeetups(meetupCriteria, pageCriteria);
    }
}