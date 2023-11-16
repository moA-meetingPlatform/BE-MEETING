package com.moa.meeting.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSimpleGetDto {
	private Long id;	//모임 id
	private String title;	//모임명
	private UUID hostUserUuid;	//모임장 UUID
	private String hostNickname;	//모임장 닉네임
	private String placeAddress;	//모임장소 주소
	private LocalDateTime meetingDatetime;	//모임시간
	private Boolean firstComeFirstServed;	//선착순 여부 : true면 선착순, false면 승인제
	private Boolean onlineStatus;	//온라인 여부 : true면 온라인, false면 오프라인
	private Integer maxParticipantsCount;	//최대 참가자 수
	private Integer currentParticipantsCount;	//현재 참가자 수
	private String meetingHeaderImageUrl;	//모임 헤더 이미지
}
