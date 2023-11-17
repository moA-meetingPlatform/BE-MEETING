package com.moa.meeting.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    @Scheduled(cron ="0 0/30 * 1/1 * ? *")
    public void checkMeetingStatus(){
        log.info("Meeting Status Check Start");
        List<LocalDateTime> meetingList = null;
        for(LocalDateTime meeting : meetingList){
            if(meeting.isBefore(LocalDateTime.now())){
                //meeting.setStatus("종료");
            }
        }
        log.info("Meeting Status Check End");
    }
}
