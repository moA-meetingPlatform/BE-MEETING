package com.moa.meeting.infrastructure.mysql;


import com.moa.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByIdIn(List<Long> ids);
}
