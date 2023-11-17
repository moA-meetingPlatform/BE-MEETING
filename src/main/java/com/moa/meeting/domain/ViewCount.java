package com.moa.meeting.domain;

import com.moa.global.domain.BaseDateTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "view_count")
public class ViewCount extends BaseDateTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "meeting_id")
    private Long meetingId;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "last_viewed_time")
    private LocalDateTime lastViewedTime;
}
