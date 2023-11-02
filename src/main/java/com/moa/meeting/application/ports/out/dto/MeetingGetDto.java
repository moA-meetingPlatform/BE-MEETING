package com.moa.meeting.application.ports.out.dto;


import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.domain.enums.MeetingStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@ToString
public class MeetingGetDto {

	private Long id;
	private String meetingTitle;
	private UUID hostUserUuid;
	private String meetingPlaceAddress;
	private String meetingDescription;
	private Integer meetingEntryFee;
	private LocalDateTime meetingDatetime;
	private Boolean firstComeFirstServed;
	private Boolean onlineStatus;
	private Integer maxParticipantsCount;
	private Integer maxAgeLimit;
	private Integer minAgeLimit;
	private String canParticipateCompanyList;
	private String entryFeeInfomationIdList;
	private String meetingParticipationQuestion;
	private String meetingHeaderImageUrl;
	private MeetingStatus meetingStatus;
	private CanParticipateGender canParticipateGender;

}
