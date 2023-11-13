package com.moa.meeting.domain;


import com.moa.global.domain.BaseDateTime;
import com.moa.meeting.domain.converter.EntryFeeInformationConverter;
import com.moa.meeting.domain.converter.MeetingStatusConverter;
import com.moa.meeting.domain.enums.EntryFeeInformation;
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
public class Meeting extends BaseDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "meeting_title", length = 150)
	private String meetingTitle;

	@Column(name = "host_user_uuid")
	private UUID hostUserUuid;

	@Column(name = "meeting_place_address", length = 255)
	private String meetingPlaceAddress;

	@Column(name = "meeting_description", columnDefinition = "TEXT")
	private String meetingDescription;

	@Column(name = "meeting_entry_fee")
	private Integer meetingEntryFee;   // 입장료

	@Column(name = "meeting_datetime")
	private LocalDateTime meetingDatetime;

	@Column(name = "first_come_first_served")
	private Boolean firstComeFirstServed; // 선착순 여부

	@Column(name = "online_status")
	private Boolean onlineStatus;   // 온라인 여부

	@Column(name = "max_participants_count")
	private Integer maxParticipantsCount;  // 최대 참가자 수

	@Column(name = "current_participants_count")
	private Integer currentParticipantsCount; // 현재 참가자 수

//	@Column(name = "max_age_limit")
//	private Integer maxAgeLimit;
//
//	@Column(name = "min_age_limit")
//	private Integer minAgeLimit;

//	@Convert(converter = CompanyCategoryConverter.class)
//	@Column(name = "can_participate_company_list", length = 10)
//	private List<CompanyCategory> canParticipateCompanyList; // 선택가능 회사그룹 id 리스트, 구분자 : ','

	// ex) C,H,N,S,M,R,P
	@Convert(converter = EntryFeeInformationConverter.class)
	@Column(name = "entry_fee_information_list", length = 20)
	private List<EntryFeeInformation> entryFeeInformationList;  // 참가비 정보 항목 리스트, 구분자 : ','

	@Column(name = "entry_fee_information_etc_string", length = 50)
	private String entryFeeInfomationEtcString;   // 참가비 정보 기타 항목 string

	@Column(name = "meeting_participation_question", length = 40)
	private String meetingParticipationQuestion;

	@Column(name = "meeting_header_image_url", length = 255)
	private String meetingHeaderImageUrl;

	@Convert(converter = MeetingStatusConverter.class)
	@Column(name = "meeting_status", length = 2)
	private MeetingStatus meetingStatus;

	//@Convert(converter = CanParticipateGenderConverter.class)
	//@Column(name = "can_participate_gender", length = 1)
	//private CanParticipateGender canParticipateGender;

}
