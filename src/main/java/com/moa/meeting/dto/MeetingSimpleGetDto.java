package com.moa.meeting.dto;

import com.moa.meeting.domain.enums.MeetingStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSimpleGetDto {
	private Long id;
	private String meetingTitle;
	private UUID hostUserUuid;
	private String meetingPlaceAddress;
	private LocalDateTime meetingDatetime;
	private Boolean firstComeFirstServed;
	private Boolean onlineStatus;
	private Integer maxParticipantsCount;
	private Integer currentParticipantsCount;
	private String meetingHeaderImageUrl;
	private MeetingStatus meetingStatus;
}
