package com.moa.meeting.dto;


import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.CanParticipateGender;
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
@NoArgsConstructor
@AllArgsConstructor
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
	private Integer currentParticipantsCount;
	private Integer maxAgeLimit;
	private Integer minAgeLimit;
	private List<Integer> canParticipateCompanyList;
	private List<Integer> entryFeeInfomationIdList;
	private String entryFeeInfomationEtcString;
	private String meetingParticipationQuestion;
	private String meetingHeaderImageUrl;
	private MeetingStatus meetingStatus;
	private CanParticipateGender canParticipateGender;


	public static MeetingGetDto fromEntity(Meeting meeting) {
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
		return MeetingGetDto.builder()
			.id(meeting.getId())
			.meetingTitle(meeting.getMeetingTitle())
			.hostUserUuid(meeting.getHostUserUuid())
			.meetingPlaceAddress(meeting.getMeetingPlaceAddress())
			.meetingDescription(meeting.getMeetingDescription())
			.meetingEntryFee(meeting.getMeetingEntryFee())
			.meetingDatetime(meeting.getMeetingDatetime())
			.firstComeFirstServed(meeting.getFirstComeFirstServed())
			.onlineStatus(meeting.getOnlineStatus())
			.maxParticipantsCount(meeting.getMaxParticipantsCount())
			.currentParticipantsCount(meeting.getCurrentParticipantsCount())
			.maxAgeLimit(meeting.getMaxAgeLimit())
			.minAgeLimit(meeting.getMinAgeLimit())
			.canParticipateCompanyList(convertCanParticipateCompanyList)
			.entryFeeInfomationIdList(convertEntryFeeInfomationIdList)
			.entryFeeInfomationEtcString(meeting.getEntryFeeInfomationEtcString())
			.meetingParticipationQuestion(meeting.getMeetingParticipationQuestion())
			.meetingHeaderImageUrl(meeting.getMeetingHeaderImageUrl())
			.meetingStatus(meeting.getMeetingStatus())
			.canParticipateGender(meeting.getCanParticipateGender())
			.build();
	}

}
