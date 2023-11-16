package com.moa.meeting.dto;


import com.moa.meeting.domain.enums.CanParticipateGender;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingGetDto {
	private Long id;	//모임 id
	private String meetingTitle;	//모임 제목
	private UUID hostUserUuid;	//호스트 uuid
	private String hostNickname;	//호스트 닉네임
	private String placeAddress;	//모임 장소 주소
	private String description;	//모임 설명
	private Integer entryFee;	//참가비
	private LocalDateTime meetingDatetime;	//모임 날짜
	private Boolean firstComeFirstServed;	//선착순 여부
	private Boolean onlineStatus;	//온라인 여부
	private Integer maxParticipants;	//최대 참가자 수
	private Integer currentParticipants;	//현재 참가자 수
	private Integer maxAge;	//최대 나이
	private Integer minAge;	//최소 나이
	private List<String> canParticipateCompanies;	//참여가능한 기업 리스트
	private List<String> entryFeeInformations;	//참가비 정보
	private String entryFeeInfomationEtcString;	//참가비 정보 기타
	private String participationQuestion;	//참가 신청 시 질문
	private String headerImageUrl;	//모임 헤더 이미지
	private CanParticipateGender participateGender;	//참여가능한 성별

}
