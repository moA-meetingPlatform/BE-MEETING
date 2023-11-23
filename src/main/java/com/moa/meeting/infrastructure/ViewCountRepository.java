package com.moa.meeting.infrastructure;

import com.moa.meeting.domain.ViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewCountRepository extends JpaRepository<ViewCount, Integer> {
    ViewCount findByMeetingId(Long meetingId);
}
