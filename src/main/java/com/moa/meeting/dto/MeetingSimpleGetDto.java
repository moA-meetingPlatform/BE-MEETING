package com.moa.meeting.dto;


import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.MeetingStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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


	public static MeetingSimpleGetDto fromEntity(Meeting meeting) {
		String canParticipateCompanyListStr = meeting.getCanParticipateCompanyList();
		String entryFeeInfomationIdListStr = meeting.getEntryFeeInfomationList();
		List<Integer> convertCanParticipateCompanyList = null;
		List<Integer> convertEntryFeeInfomationIdList = Collections.emptyList();

		if (canParticipateCompanyListStr != null) {
			String[] canParticipateCompanyListStrArr = canParticipateCompanyListStr.split(",");
			convertCanParticipateCompanyList = Arrays.stream(canParticipateCompanyListStrArr)    // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		}

		if (entryFeeInfomationIdListStr != null) {
			String[] entryFeeInfomationIdListStrArr = entryFeeInfomationIdListStr.split(",");
			convertEntryFeeInfomationIdList = Arrays.stream(entryFeeInfomationIdListStrArr)    // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		}
		return MeetingSimpleGetDto.builder()
			.id(meeting.getId())
			.meetingTitle(meeting.getMeetingTitle())
			.hostUserUuid(meeting.getHostUserUuid())
			.meetingPlaceAddress(meeting.getMeetingPlaceAddress())
			.meetingDatetime(meeting.getMeetingDatetime())
			.firstComeFirstServed(meeting.getFirstComeFirstServed())
			.onlineStatus(meeting.getOnlineStatus())
			.maxParticipantsCount(meeting.getMaxParticipantsCount())
			.currentParticipantsCount(meeting.getCurrentParticipantsCount())
			.meetingHeaderImageUrl(meeting.getMeetingHeaderImageUrl())
			.meetingStatus(meeting.getMeetingStatus())
			.build();
	}

}
