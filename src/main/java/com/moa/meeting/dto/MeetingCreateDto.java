package com.moa.meeting.dto;


import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.domain.enums.CompanyCategory;
import com.moa.meeting.domain.enums.EntryFeeInformation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCreateDto {

	private String title;	//모임명
	private UUID hostUserUuid;	//모임장 UUID
	private String hostNickname;	//모임장 닉네임
	private String placeAddress;	//모임장소 주소
	private String description;	//모임설명
	private Integer entryFee;	//참가비
	private LocalDateTime meetingDatetime;	//모임시간
	private Boolean firstComeFirstServed;	//선착순 여부 : true면 선착순, false면 승인제
	private Boolean onlineStatus;	//온라인 여부 : true면 온라인, false면 오프라인
	private Integer maxParticipants;	//최대 참가자 수
	private Integer maxAge;	//최대 나이
	private Integer minAge;	//최소 나이
	private List<String> participateCompanies;	//참여가능한 기업 리스트
	private List<EntryFeeInformation> entryFeeInformations;	//참가비 정보
	private String entryFeeInfomationEtcString;	//참가비 정보 기타
	private String participationQuestion;	//참가 신청 시 질문
	private String headerImageUrl;	//모임 헤더 이미지
	private CanParticipateGender participateGender;	//참여가능한 성별
	
}
