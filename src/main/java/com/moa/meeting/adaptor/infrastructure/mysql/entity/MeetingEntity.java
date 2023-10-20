package com.moa.meeting.adaptor.infrastructure.mysql.entity;


import com.moa.meeting.adaptor.infrastructure.mysql.converter.BaseEnumConverter;
import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.JoinGender;
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

	@Column(name = "title", length = 40)
	private String title;

	@Column(name = "host_user_uuid")
	private UUID hostUserUuid;

	@Column(name = "meeting_address", length = 255)
	private String meetingAddress;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(name = "entry_fee")
	private Integer entryFee;   // 입장료

	@Column(name = "meeting_datetime")
	private LocalDateTime meetingDatetime;

	@Column(columnDefinition = "TEXT", name = "refund_policy")
	private String refundPolicy;

	@Column(name = "is_fcfs")
	private Boolean isFcfs; // 선착순 여부

	@Column(name = "is_online")
	private Boolean isOnline;   // 온라인 여부

	@Column(name = "meeting_use")
	private Boolean meetingUse; // 모임 사용 여부

	@Column(name = "max_participant_num")
	private Integer maxParticipantNum;  // 최대 참가자 수

	@Column(name = "curr_participant_num")
	private Integer currParticipantNum; // 현재 참가자 수

	@Column(name = "max_age")
	private Integer maxAge;

	@Column(name = "min_age")
	private Integer minAge;

	@Column(name = "company_list", length = 255)
	private String companyList; // 선택가능 회사 리스트, 구분자 : ','

	@Column(name = "entre_fee_info_id_list", length = 50)
	private String entreFeeInfoIdList;   // 참가비 정보 항목 id 리스트, 구분자 : ','

	@Column(name = "entre_fee_info_etc_string", length = 50)
	private String entreFeeInfoEtcString;   // 참가비 정보 기타 항목 string

	@Column(name = "theme_category_id")
	private Integer themeCategoryId;

	@Column(name = "question", length = 40)
	private String question;

	@Column(name = "header_image_url", length = 255)
	private String headerImageUrl;

	@Convert(converter = BaseEnumConverter.MeetingStatusConverter.class)
	@Column(name = "meeting_status", length = 2)
	private MeetingStatus meetingStatus;

	@Convert(converter = BaseEnumConverter.JoinGenderConverter.class)
	@Column(name = "join_gender", length = 1)
	private JoinGender joinGender;


	/**
	 * 모임 생성
	 *
	 * @param title
	 * @param hostUserUuid
	 * @param meetingAddress
	 * @param description
	 * @param entryFee
	 * @param meetingDatetime
	 * @param refundPolicy
	 * @param isFcfs
	 * @param isOnline
	 * @param maxParticipantNum
	 * @param maxAge
	 * @param minAge
	 * @param companyList
	 * @param entreFeeInfoIdList
	 * @param entreFeeInfoEtcString
	 * @param themeCategoryId
	 * @param question
	 * @param headerImageUrl
	 * @param meetingStatus
	 * @param joinGender
	 * @return MeetingEntity Meeting Domain JPA 구현체
	 */
	public static MeetingEntity createMeeting(String title, UUID hostUserUuid, String meetingAddress, String description, int entryFee, LocalDateTime meetingDatetime, String refundPolicy,
		boolean isFcfs, boolean isOnline, int maxParticipantNum, int maxAge, int minAge, List<String> companyList, List<Integer> entreFeeInfoIdList, String entreFeeInfoEtcString, int themeCategoryId,
		String question,
		String headerImageUrl, MeetingStatus meetingStatus, JoinGender joinGender) {

		String CompanyListStr = null;
		String entreFeeInfoIdListStr = null;

		//
		if (companyList != null) {
			// 회사 그룹 List ->  String list to String, delemeter : ','
			CompanyListStr = String.join(",", companyList);
		}
		if (entreFeeInfoIdList != null) {
			// 참가비 id List -> Integer list to String, delemeter : ','
			entreFeeInfoIdListStr = entreFeeInfoIdList.toString().replaceAll(" ", "");
			entreFeeInfoIdListStr = entreFeeInfoIdListStr.substring(1, entreFeeInfoIdListStr.length() - 1);
		}

		return MeetingEntity.builder()
			.title(title)
			.hostUserUuid(hostUserUuid)
			.meetingAddress(meetingAddress)
			.description(description)
			.entryFee(entryFee)
			.meetingDatetime(meetingDatetime)
			.refundPolicy(refundPolicy)
			.isFcfs(isFcfs)
			.isOnline(isOnline)
			.meetingUse(true)
			.maxParticipantNum(maxParticipantNum)
			.currParticipantNum(0)
			.maxAge(maxAge)
			.minAge(minAge)
			.companyList(CompanyListStr)
			.entreFeeInfoIdList(entreFeeInfoIdListStr)
			.entreFeeInfoEtcString(entreFeeInfoEtcString)
			.themeCategoryId(themeCategoryId)
			.question(question)
			.headerImageUrl(headerImageUrl)
			.meetingStatus(meetingStatus)
			.joinGender(joinGender)
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
		// String으로 저장된 companyList, entreFeeInfoIdList를 List로 변환
		String companyListStr = this.companyList;
		String entreFeeInfoIdListStr = this.entreFeeInfoIdList;
		List<String> convertCompanyList;
		List<Integer> convertEntreFeeInfoIdList = Collections.emptyList();

		if (companyListStr == null) {
			// 회사그룹 없을 경우 모든 회사로 설정
			convertCompanyList = Collections.singletonList("ALL");
		} else {
			convertCompanyList = Arrays.asList(companyListStr.split(",")); // 구분자 : ','
		}

		if (entreFeeInfoIdListStr != null) {
			String[] entreFeeInfoIdListStrArr = entreFeeInfoIdListStr.split(",");
			convertEntreFeeInfoIdList = Arrays.stream(entreFeeInfoIdListStrArr)    // stream of String
				.map(Integer::valueOf) // stream of Integer
				.collect(Collectors.toList());
		}

		return Meeting.builder()
			.id(this.id)
			.title(this.title)
			.hostUserUuid(this.hostUserUuid)
			.meetingAddress(this.meetingAddress)
			.description(this.description)
			.entryFee(this.entryFee)
			.meetingDatetime(this.meetingDatetime)
			.refundPolicy(this.refundPolicy)
			.isFcfs(this.isFcfs)
			.isOnline(this.isOnline)
			.meetingUse(this.meetingUse)
			.maxParticipantNum(this.maxParticipantNum)
			.currParticipantNum(this.currParticipantNum)
			.maxAge(this.maxAge)
			.minAge(this.minAge)
			.companyList(convertCompanyList)
			.entreFeeInfoIdList(convertEntreFeeInfoIdList)
			.entreFeeInfoEtcString(this.entreFeeInfoEtcString)
			.themeCategoryId(this.themeCategoryId)
			.question(this.question)
			.headerImageUrl(this.headerImageUrl)
			.meetingStatus(this.meetingStatus)
			.joinGender(this.joinGender)
			.createDatetime(this.getCreateDatetime())
			.updateDatetime(this.getUpdateDatetime())
			.build();
	}

}
