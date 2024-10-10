package az.ingress.scheduler;

import az.ingress.service.abstraction.MeetupService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetupScheduler {
    private final MeetupService meetupService;

    @Scheduled(fixedDelayString = "PT1H")
    @SchedulerLock(name = "updateExpiredMeetupStatus", lockAtLeastForString = "PT1M", lockAtMostForString = "PT3M")
    public void updateExpiredMeetupStatus() {
        meetupService.updateExpiredMeetupStatus();
    }
}