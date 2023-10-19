package com.moa.meeting.application.ports.out.dto;


import com.moa.meeting.domain.enums.JoinGender;
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
	private String title;
	private UUID hostUserUuid;
	private String meetingAddress;
	private String description;
	private Integer entryFee;
	private LocalDateTime meetingDatetime;
	private String refundPolicy;
	private Boolean isFcfs;
	private Boolean isOnline;
	private Integer maxParticipantNum;
	private Integer maxAge;
	private Integer minAge;
	private String companyList;
	private Integer themeCategoryId;
	private String question;
	private String headerImageUrl;
	private MeetingStatus meetingStatus;
	private JoinGender joinGender;

}
