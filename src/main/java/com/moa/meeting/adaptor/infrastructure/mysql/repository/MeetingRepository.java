package com.moa.meeting.adaptor.infrastructure.mysql.repository;


import com.moa.meeting.adaptor.infrastructure.mysql.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {
}
