package com.moa.meeting.vo.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSimpleResponse {

	private Long id;	//모임 id
	private String title;	//모임 제목
	private UUID hostUserUuid;	//모임 개설자 uuid
	private String hostNickname;	//모임 개설자 닉네임
	private String placeAddress;	//모임 장소 주소
	private LocalDateTime meetingDatetime;	//모임 시간
	private String firstComeFirstServed;	// 0-선착순, 1-승인제
	private String onlineStatus;	// 0-오프라인, 1-온라인
	private Integer maxParticipants;	//최대 참여 인원
	private Integer currentParticipants;	//현재 참여 인원
	private String meetingHeaderImageUrl;	//모임 헤더 이미지 url
	private String meetingStatus;	//모집중, 모집완료, 모집취소, 모임종료, 삭제
}
