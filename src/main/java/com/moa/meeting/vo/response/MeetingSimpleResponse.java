package com.moa.meeting.vo.response;


import com.moa.meeting.domain.enums.MeetingStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public class MeetingSimpleResponse {

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
