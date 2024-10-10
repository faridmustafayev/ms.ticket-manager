package az.ingress.mapper;

import az.ingress.dao.entity.MeetupEntity;
import az.ingress.model.common.PageableResponse;
import az.ingress.model.enums.MeetupStatus;
import az.ingress.model.request.CreateMeetupRequest;
import az.ingress.model.response.MeetupResponse;
import org.springframework.data.domain.Page;

import static az.ingress.model.enums.MeetupStatus.ACTIVE;

public enum MeetupMapper {
    MEETUP_MAPPER;

    public MeetupEntity buildMeetupEntity(CreateMeetupRequest meetupRequest) {
        return MeetupEntity.builder()
                           .name(meetupRequest.getName())
                           .description(meetupRequest.getDescription())
                           .price(meetupRequest.getPrice())
                           .eventDate(meetupRequest.getEventDate())
                           .status(ACTIVE)
                           .build();
    }

    public PageableResponse<MeetupResponse> buildPageableResponse(Page<MeetupEntity> meetupEntityList) {
        return PageableResponse.<MeetupResponse>builder()
                               .content(meetupEntityList.stream().map(MEETUP_MAPPER::toMeetupResponse).toList())
                               .totalElements(meetupEntityList.getTotalElements())
                               .totalPages(meetupEntityList.getTotalPages())
                               .hasNextPage(meetupEntityList.hasNext())
                               .build();
    }

    public void setStatus(MeetupEntity meetupEntity, MeetupStatus status) {
        meetupEntity.setStatus(status);
    }

    private MeetupResponse toMeetupResponse(MeetupEntity meetupEntity) {
        return MeetupResponse.builder()
                             .id(meetupEntity.getId())
                             .name(meetupEntity.getName())
                             .price(meetupEntity.getPrice())
                             .description(meetupEntity.getDescription())
                             .eventDate(meetupEntity.getEventDate())
                             .build();
    }
}