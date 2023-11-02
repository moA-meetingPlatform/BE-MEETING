package com.moa.meeting.infrastructure.mysql;


import com.moa.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
