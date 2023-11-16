package com.moa.meeting.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDetailGetDto {
    private String title;   //모임 제목
    private UUID hostUuid;    //호스트 uuid
    private String hostNickname;    //호스트 닉네임
    private String placeAddress;    //모임 장소 주소
    private String description; //모임 설명
    private Integer entryFee;   //참가비
    private String meetingDatetime; //모임 날짜
    private String firstComeFirstServed;   //선착순 여부
    private String onlineStatus;   //온라인 여부
    private Integer maxParticipants;    //최대 참가자 수
    private Integer currentParticipants;    //현재 참가자 수
    private List<String> entryFeeInformations;  //참가비 정보
    private String entryFeeInfomationEtcString; //참가비 정보 기타
    private String participationQuestion;   //참가 신청 시 질문
    private String headerImageUrl;  //모임 헤더 이미지
    private String meetingStatus;	//모임상태 : 모집중, 모집완료, 모집취소, 모임종료, 삭제
}
