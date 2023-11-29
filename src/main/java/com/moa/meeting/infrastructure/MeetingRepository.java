package com.moa.meeting.infrastructure;


import com.moa.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByIdIn(List<Long> ids);   // id로 모임 조회

    // 최근에 생성된 모임
    @Query("SELECT m FROM Meeting m WHERE m.id IN :ids ORDER BY m.createDatetime DESC")
    List<Meeting> findNewMeetingsByIds(@Param("ids") List<Long> meetingIds);

    // 마감임박순
    @Query("SELECT m FROM Meeting m WHERE m.id IN :ids AND m.meetingDatetime > CURRENT_TIMESTAMP ORDER BY m.meetingDatetime ASC")
    List<Meeting> findSoaringMeetingsByIds(@Param("ids") List<Long> meetingIds);

    //모임 추천순
    @Query("SELECT m FROM Meeting m WHERE m.id IN :ids")
    List<Meeting> findByIds(@Param("ids") List<Long> meetingIds);
}
