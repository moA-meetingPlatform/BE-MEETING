package com.moa.meeting.domain;


import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.domain.enums.MeetingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public class Meeting {

	private Long id;
	private String meetingTitle;
	private UUID hostUserUuid;
	private String meetingPlaceAddress;
	private String meetingDescription;
	private Integer meetingEntryFee;   // 입장료
	private LocalDateTime meetingDatetime;
	private Boolean firstComeFirstServed; // 선착순 여부
	private Boolean onlineStatus;   // 온라인 여부
	private Integer maxParticipantsCount;  // 최대 참가자 수
	private Integer currentParticipantsCount; // 현재 참가자 수
	private Integer maxAgeLimit;
	private Integer minAgeLimit;
	private List<Integer> canParticipateCompanyList;
	private List<Integer> entryFeeInfomationIdList;   // 참가비 정보 항목 id 리스트
	private String entryFeeInfomationEtcString;   // 참가비 정보 기타 항목 string
	private String meetingParticipationQuestion; // 모임 참가시 필요한 질문(선착순 아닐 경우)
	private String meetingHeaderImageUrl;

	// enum
	private MeetingStatus meetingStatus;
	private CanParticipateGender canParticipateGender;

	// create, update datetime
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;


	/**
	 * 모임 생성
	 *
	 * @param meetingTitle                 모임 제목
	 * @param hostUserUuid                 모임 개설자 uuid
	 * @param meetingPlaceAddress          모임 주소
	 * @param meetingDescription           모임 설명
	 * @param meetingEntryFee              모임 입장료
	 * @param meetingDatetime              모임 시작 시간
	 * @param firstComeFirstServed         선착순 여부
	 * @param onlineStatus                 온라인 여부
	 * @param maxParticipantsCount         최대 참가자 수
	 * @param maxAgeLimit                  최대 나이
	 * @param minAgeLimit                  최소 나이
	 * @param canParticipateCompanyList    참가 가능한 회사 그룹 리스트
	 * @param entryFeeInfomationIdList     참가비 정보 항목 id 리스트 (모임 비용이 있을 경우 설정될 수 있음)
	 * @param entryFeeInfomationEtcString  참가비 정보 기타 항목 string (모임 비용이 있을 경우 설정될 수 있음)
	 * @param meetingParticipationQuestion 모임 참가시 필요한 질문(선착순 아닐 경우)
	 * @param meetingHeaderImageUrl        모임 헤더 이미지 url
	 * @param canParticipateGender         참가 가능한 성별
	 * @return Meeting Domain
	 */
	public static Meeting saveMeeting(String meetingTitle, UUID hostUserUuid, String meetingPlaceAddress, String meetingDescription, int meetingEntryFee, LocalDateTime meetingDatetime,
		boolean firstComeFirstServed, boolean onlineStatus, int maxParticipantsCount, int maxAgeLimit, int minAgeLimit, List<Integer> canParticipateCompanyList, List<Integer> entryFeeInfomationIdList,
		String entryFeeInfomationEtcString,
		String meetingParticipationQuestion,
		String meetingHeaderImageUrl, CanParticipateGender canParticipateGender) {
		return Meeting.builder()
			.meetingTitle(meetingTitle)
			.hostUserUuid(hostUserUuid)
			.meetingPlaceAddress(meetingPlaceAddress)
			.meetingDescription(meetingDescription)
			.meetingEntryFee(meetingEntryFee)
			.meetingDatetime(meetingDatetime)
			.firstComeFirstServed(firstComeFirstServed)
			.onlineStatus(onlineStatus)
			.maxParticipantsCount(maxParticipantsCount)
			.currentParticipantsCount(0)
			.maxAgeLimit(maxAgeLimit)
			.minAgeLimit(minAgeLimit)
			.canParticipateCompanyList(canParticipateCompanyList)
			.entryFeeInfomationIdList(entryFeeInfomationIdList)
			.entryFeeInfomationEtcString(entryFeeInfomationEtcString)
			.meetingParticipationQuestion(meetingParticipationQuestion)
			.meetingHeaderImageUrl(meetingHeaderImageUrl)
			.meetingStatus(MeetingStatus.RECRUIT_IN_PROGRESS)
			.canParticipateGender(canParticipateGender)
			.build();
	}

}
