package com.moa.meeting.domain;


import com.moa.meeting.adaptor.infrastructure.mysql.entity.MeetingEntity;
import com.moa.meeting.domain.enums.JoinGender;
import com.moa.meeting.domain.enums.MeetingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Builder
@AllArgsConstructor
public class Meeting {

	private Long id;
	private String title;
	private UUID hostUserUuid;
	private String meetingAddress;
	private String description;
	private Integer entryFee;   // 입장료
	private LocalDateTime meetingDatetime;
	private String refundPolicy;
	private Boolean isFcfs; // 선착순 여부
	private Boolean isOnline;   // 온라인 여부
	private Boolean meetingUse;
	private Integer maxParticipantNum;  // 최대 참가자 수
	private Integer currParticipantNum; // 현재 참가자 수
	private Integer maxAge;
	private Integer minAge;
	private List<String> companyList;
	private List<Integer> entreFeeInfoIdList;   // 참가비 정보 항목 id 리스트
	private String entreFeeInfoEtcString;   // 참가비 정보 기타 항목 string
	private Integer themeCategoryId;
	private String question;
	private String headerImageUrl;

	// enum
	private MeetingStatus meetingStatus;
	private JoinGender joinGender;

	// create, update datetime
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;


	public static Meeting saveMeeting(String title, UUID hostUserUuid, String meetingAddress, String description, int entryFee, LocalDateTime meetingDatetime, String refundPolicy,
		boolean isFcfs, boolean isOnline, int maxParticipantNum, int maxAge, int minAge, List<String> companyList, List<Integer> entreFeeInfoIdList, String entreFeeInfoEtcString, int themeCategoryId,
		String question,
		String headerImageUrl, JoinGender joinGender) {
		return Meeting.builder()
			.title(title)
			.hostUserUuid(hostUserUuid)
			.meetingAddress(meetingAddress)
			.description(description)
			.entryFee(entryFee)
			.meetingDatetime(meetingDatetime)
			.refundPolicy(refundPolicy)
			.isFcfs(isFcfs)
			.isOnline(isOnline)
			.maxParticipantNum(maxParticipantNum)
			.currParticipantNum(0)
			.maxAge(maxAge)
			.minAge(minAge)
			.companyList(companyList)
			.entreFeeInfoIdList(entreFeeInfoIdList)
			.entreFeeInfoEtcString(entreFeeInfoEtcString)
			.themeCategoryId(themeCategoryId)
			.question(question)
			.headerImageUrl(headerImageUrl)
			.meetingStatus(MeetingStatus.RECRUIT_IN_PROGRESS)
			.joinGender(joinGender)
			.build();
	}


	public static Meeting fromEntity(MeetingEntity meetingEntity) {
		String companyListStr = meetingEntity.getCompanyList();
		String entreFeeInfoIdListStr = meetingEntity.getEntreFeeInfoIdList();
		List<String> companyList;
		List<Integer> entreFeeInfoIdList = Collections.emptyList();

		if (companyListStr == null) {
			companyList = Collections.singletonList("ALL");
		} else {
			companyList = Arrays.asList(companyListStr.split(",")); // 구분자 : ','
		}

		if (entreFeeInfoIdListStr != null) {
			String[] entreFeeInfoIdListStrArr = entreFeeInfoIdListStr.split(",");
			entreFeeInfoIdList = Arrays.stream(entreFeeInfoIdListStrArr)    // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		}

		return Meeting.builder()
			.id(meetingEntity.getId())
			.title(meetingEntity.getTitle())
			.hostUserUuid(meetingEntity.getHostUserUuid())
			.meetingAddress(meetingEntity.getMeetingAddress())
			.description(meetingEntity.getDescription())
			.entryFee(meetingEntity.getEntryFee())
			.meetingDatetime(meetingEntity.getMeetingDatetime())
			.refundPolicy(meetingEntity.getRefundPolicy())
			.isFcfs(meetingEntity.getIsFcfs())
			.isOnline(meetingEntity.getIsOnline())
			.meetingUse(meetingEntity.getMeetingUse())
			.maxParticipantNum(meetingEntity.getMaxParticipantNum())
			.currParticipantNum(meetingEntity.getCurrParticipantNum())
			.maxAge(meetingEntity.getMaxAge())
			.minAge(meetingEntity.getMinAge())
			.companyList(companyList)
			.entreFeeInfoIdList(entreFeeInfoIdList)
			.entreFeeInfoEtcString(meetingEntity.getEntreFeeInfoEtcString())
			.themeCategoryId(meetingEntity.getThemeCategoryId())
			.question(meetingEntity.getQuestion())
			.headerImageUrl(meetingEntity.getHeaderImageUrl())
			.meetingStatus(meetingEntity.getMeetingStatus())
			.joinGender(meetingEntity.getJoinGender())
			.createDatetime(meetingEntity.getCreateDatetime())
			.updateDatetime(meetingEntity.getUpdateDatetime())
			.build();
	}

}
