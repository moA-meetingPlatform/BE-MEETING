package com.moa.meeting.adaptor.infrastructure.mysql.entity;


import com.moa.meeting.adaptor.infrastructure.mysql.converter.BaseEnumConverter;
import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.domain.enums.MeetingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "meeting")
public class MeetingEntity extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "meeting_title", length = 40)
	private String meetingTitle;

	@Column(name = "host_user_uuid")
	private UUID hostUserUuid;

	@Column(name = "meeting_address", length = 255)
	private String meetingPlaceAddress;

	@Column(name = "meeting_description", columnDefinition = "TEXT")
	private String meetingDescription;

	@Column(name = "entry_fee")
	private Integer meetingEntryFee;   // 입장료

	@Column(name = "meeting_datetime")
	private LocalDateTime meetingDatetime;

	@Column(name = "is_fcfs")
	private Boolean firstComeFirstServed; // 선착순 여부

	@Column(name = "is_online")
	private Boolean onlineStatus;   // 온라인 여부

	@Column(name = "max_participant_num")
	private Integer maxParticipantsCount;  // 최대 참가자 수

	@Column(name = "curr_participant_num")
	private Integer currentParticipantsCount; // 현재 참가자 수

	@Column(name = "max_age")
	private Integer maxAgeLimit;

	@Column(name = "min_age")
	private Integer minAgeLimit;

	@Column(name = "can_participate_company_list", length = 255)
	private String canParticipateCompanyList; // 선택가능 회사그룹 id 리스트, 구분자 : ','

	@Column(name = "entry_fee_info_id_list", length = 50)
	private String entryFeeInfomationIdList;   // 참가비 정보 항목 id 리스트, 구분자 : ','

	@Column(name = "entry_fee_info_etc_string", length = 50)
	private String entryFeeInfomationEtcString;   // 참가비 정보 기타 항목 string

	@Column(name = "meeting_participation_question", length = 40)
	private String meetingParticipationQuestion;

	@Column(name = "header_image_url", length = 255)
	private String meetingHeaderImageUrl;

	@Convert(converter = BaseEnumConverter.MeetingStatusConverter.class)
	@Column(name = "meeting_status", length = 2)
	private MeetingStatus meetingStatus;

	@Convert(converter = BaseEnumConverter.CanParticipateGenderConverter.class)
	@Column(name = "join_gender", length = 1)
	private CanParticipateGender canParticipateGender;


	/**
	 * 모임 생성
	 *
	 * @param meetingTitle
	 * @param hostUserUuid
	 * @param meetingPlaceAddress
	 * @param meetingDescription
	 * @param meetingEntryFee
	 * @param meetingDatetime
	 * @param firstComeFirstServed
	 * @param onlineStatus
	 * @param maxParticipantsCount
	 * @param maxAgeLimit
	 * @param minAgeLimit
	 * @param canParticipateCompanyList
	 * @param entryFeeInfomationIdList
	 * @param entryFeeInfomationEtcString
	 * @param meetingParticipationQuestion
	 * @param meetingHeaderImageUrl
	 * @param meetingStatus
	 * @param canParticipateGender
	 * @return MeetingEntity Meeting Domain JPA 구현체
	 */
	public static MeetingEntity createMeeting(String meetingTitle, UUID hostUserUuid, String meetingPlaceAddress, String meetingDescription, int meetingEntryFee, LocalDateTime meetingDatetime,
		boolean firstComeFirstServed, boolean onlineStatus, int maxParticipantsCount, int maxAgeLimit, int minAgeLimit, List<Integer> canParticipateCompanyList, List<Integer> entryFeeInfomationIdList,
		String entryFeeInfomationEtcString,
		String meetingParticipationQuestion,
		String meetingHeaderImageUrl, MeetingStatus meetingStatus, CanParticipateGender canParticipateGender) {

		String CompanyListStr = null;
		String entryFeeInfomationIdListStr = null;

		//
		if (canParticipateCompanyList != null) {
			// 회사 그룹 List ->  Integer list to String, delemeter : ','
			CompanyListStr = canParticipateCompanyList.toString().replaceAll(" ", "");   // 공백 제거
			CompanyListStr = CompanyListStr.substring(1, CompanyListStr.length() - 1);  // [ ] 제거
		}
		if (entryFeeInfomationIdList != null) {
			// 참가비 id List -> Integer list to String, delemeter : ','
			entryFeeInfomationIdListStr = entryFeeInfomationIdList.toString().replaceAll(" ", "");
			entryFeeInfomationIdListStr = entryFeeInfomationIdListStr.substring(1, entryFeeInfomationIdListStr.length() - 1);
		}

		return MeetingEntity.builder()
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
			.canParticipateCompanyList(CompanyListStr)
			.entryFeeInfomationIdList(entryFeeInfomationIdListStr)
			.entryFeeInfomationEtcString(entryFeeInfomationEtcString)
			.meetingParticipationQuestion(meetingParticipationQuestion)
			.meetingHeaderImageUrl(meetingHeaderImageUrl)
			.meetingStatus(MeetingStatus.RECRUIT_IN_PROGRESS)
			.canParticipateGender(canParticipateGender)
			.build();
	}


	/**
	 * MeetingEntity -> Meeting Domain
	 * Meeting 도메인 구현체인 Entity를 도메인으로 변환
	 * 의존성 역전
	 *
	 * @return Meeting 모임 도메인
	 */
	public Meeting toDomain() {
		// String으로 저장된 canParticipateCompanyList, entryFeeInfomationIdList를 List로 변환
		String canParticipateCompanyListStr = this.canParticipateCompanyList;
		String entryFeeInfomationIdListStr = this.entryFeeInfomationIdList;
		List<Integer> convertCanParticipateCompanyList = null;
		List<Integer> convertEntryFeeInfomationIdList = Collections.emptyList();

		//		if (canParticipateCompanyListStr == null) {
		//			// 회사그룹 없을 경우 모든 회사로 설정
		//			convertCanParticipateCompanyListStr = Collections.singletonList("ALL");
		//		} else {
		//			convertCanParticipateCompanyListStr = Arrays.asList(canParticipateCompanyListStr.split(",")); // 구분자 : ','
		//		}

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

		return Meeting.builder()
			.id(this.id)
			.meetingTitle(this.meetingTitle)
			.hostUserUuid(this.hostUserUuid)
			.meetingPlaceAddress(this.meetingPlaceAddress)
			.meetingDescription(this.meetingDescription)
			.meetingEntryFee(this.meetingEntryFee)
			.meetingDatetime(this.meetingDatetime)
			.firstComeFirstServed(this.firstComeFirstServed)
			.onlineStatus(this.onlineStatus)
			.maxParticipantsCount(this.maxParticipantsCount)
			.currentParticipantsCount(this.currentParticipantsCount)
			.maxAgeLimit(this.maxAgeLimit)
			.minAgeLimit(this.minAgeLimit)
			.canParticipateCompanyList(convertCanParticipateCompanyList)
			.entryFeeInfomationIdList(convertEntryFeeInfomationIdList)
			.entryFeeInfomationEtcString(this.entryFeeInfomationEtcString)
			.meetingParticipationQuestion(this.meetingParticipationQuestion)
			.meetingHeaderImageUrl(this.meetingHeaderImageUrl)
			.meetingStatus(this.meetingStatus)
			.canParticipateGender(this.canParticipateGender)
			.createDatetime(this.getCreateDatetime())
			.updateDatetime(this.getUpdateDatetime())
			.build();
	}

}
