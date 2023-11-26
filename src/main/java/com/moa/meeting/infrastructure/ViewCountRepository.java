package com.moa.meeting.infrastructure;

import com.moa.meeting.domain.ViewCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViewCountRepository extends JpaRepository<ViewCount, Integer> {
    ViewCount findByMeetingId(Long meetingId);
    @Query("SELECT vc.meetingId FROM ViewCount vc ORDER BY vc.viewCount DESC")
    List<Long> findTopMeetingIdsByViewCount();
}
