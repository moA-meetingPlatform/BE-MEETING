package com.moa.meeting.domain;


import com.moa.global.domain.BaseDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

	@Column(name = "title", length = 150)	//모임 제목
	private String title;

	@Column(name = "host_user_uuid")	//모임 개설자 uuid
	private UUID hostUserUuid;

	@Column(name = "host_nickname", length = 10)	//모임 개설자 이름
	private String hostNickname;

	@Column(name = "place_address", length = 255)	//모임 장소 주소
	private String placeAddress;

	@Column(name = "description", length = 7000)	//모임 설명
	private String description;

	@Column(name = "entry_fee")
	private Integer entryFee;   // 입장료

	@Column(name = "meeting_datetime")	//모임 시작 시간
	private LocalDateTime meetingDatetime;

	@Column(name = "first_come_first_served")
	private Boolean firstComeFirstServed; // 선착순 여부

	@Column(name = "online_status")
	private Boolean onlineStatus;   // 온라인 여부

	@Column(name = "max_participants")
	private Integer maxParticipants;  // 최대 참가자 수

	@Column(name = "current_participants")
	private Integer currentParticipants; // 현재 참가자 수

	// ex) C,H,N,S,M,R,P
	@Column(name = "entry_fee_informations", length = 20)
	private String entryFeeInformations;  // 참가비 정보 항목 리스트, 구분자 : ','

	@Column(name = "entry_fee_information_etc_string", length = 50)
	private String entryFeeInfomationEtcString;   // 참가비 정보 기타 항목 string

	@Column(name = "participation_question", length = 40)	//모임 참가시 필요한 질문(선착순 아닐 경우)
	private String participationQuestion;

	@Column(name = "header_image_url", length = 255)	//모임 헤더 이미지 url
	private String headerImageUrl;


}
