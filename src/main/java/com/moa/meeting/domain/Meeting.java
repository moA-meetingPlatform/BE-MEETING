package com.moa.meeting.domain;


import com.moa.meeting.domain.enums.JoinGender;
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


	/**
	 * 모임 생성
	 *
	 * @param title                 모임 제목
	 * @param hostUserUuid          모임 개설자 uuid
	 * @param meetingAddress        모임 주소
	 * @param description           모임 설명
	 * @param entryFee              모임 입장료
	 * @param meetingDatetime       모임 시작 시간
	 * @param refundPolicy          환불 정책
	 * @param isFcfs                선착순 여부
	 * @param isOnline              온라인 여부
	 * @param maxParticipantNum     최대 참가자 수
	 * @param maxAge                최대 나이
	 * @param minAge                최소 나이
	 * @param companyList           참가 가능한 회사 그룹 리스트
	 * @param entreFeeInfoIdList    참가비 정보 항목 id 리스트 (모임 비용이 있을 경우 설정될 수 있음)
	 * @param entreFeeInfoEtcString 참가비 정보 기타 항목 string (모임 비용이 있을 경우 설정될 수 있음)
	 * @param themeCategoryId       테마 카테고리 id
	 * @param question              모임 참가시 필요한 질문(선착순 아닐 경우)
	 * @param headerImageUrl        모임 헤더 이미지 url
	 * @param joinGender            참가 가능한 성별
	 * @return Meeting Meeting Domain
	 */
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

}
