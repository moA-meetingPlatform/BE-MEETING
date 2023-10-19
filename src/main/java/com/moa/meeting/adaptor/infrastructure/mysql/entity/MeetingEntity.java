package com.moa.meeting.adaptor.infrastructure.mysql.entity;


import com.moa.meeting.adaptor.infrastructure.mysql.converter.BaseEnumConverter;
import com.moa.meeting.domain.enums.JoinGender;
import com.moa.meeting.domain.enums.MeetingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


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


	public static MeetingEntity createMeeting(String title, UUID hostUserUuid, String meetingAddress, String description, int entryFee, LocalDateTime meetingDatetime, String refundPolicy,
		boolean isFcfs, boolean isOnline, int maxParticipantNum, int maxAge, int minAge, List<String> companyList, List<Integer> entreFeeInfoIdList, String entreFeeInfoEtcString, int themeCategoryId,
		String question,
		String headerImageUrl, MeetingStatus meetingStatus, JoinGender joinGender) {

		// String list to String
		String CompanyListStr = String.join(",", companyList);

		// Integer list to String
		String entreFeeInfoIdListStr = entreFeeInfoIdList.toString().replaceAll(" ", "");
		entreFeeInfoIdListStr = entreFeeInfoIdListStr.substring(1, entreFeeInfoIdListStr.length() - 1);

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

}
