package com.moa.meeting.adaptor.web.request;


import com.moa.meeting.domain.enums.JoinGender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@Schema(description = "모임 생성 request")
@Getter
@ToString
public class MeetingCreateReq {

	@Schema(description = "모임 제목", nullable = false, example = "할로윈 파티!")
	private String title;
	@Schema(description = "주소(오프라인인 경우)", nullable = true, example = "부산시 해운대구")
	private String meetingAddress;
	@Schema(description = "모임 상세 설명", nullable = true, example = "할로윈 파티 오실래요?\n박쥐 쿠키, 호박타르트가 준비되어 있어요!!")
	private String description;
	@Schema(description = "모임 비용", nullable = false, example = "10000")
	private Integer entryFee;
	@Schema(description = "모임 시작 시간", nullable = false, example = "2021-10-31T19:30:00")
	private LocalDateTime meetingDatetime;
	@Schema(description = "환불 정책", nullable = true, example = "환불 불가")
	private String refundPolicy;
	@Schema(description = "선착순 여부", nullable = false, example = "true")
	private Boolean isFcfs;
	@Schema(description = "온라인 여부", nullable = false, example = "false")
	private Boolean isOnline;
	@Schema(description = "참가 인원수", nullable = false, example = "10")
	private Integer maxParticipantNum;
	@Schema(description = "최대 나이", nullable = true, example = "30")
	private Integer maxAge;
	@Schema(description = "최소 나이", nullable = true, example = "25")
	private Integer minAge;
	@Schema(description = "참가 가능한 회사 그룹 리스트", nullable = true, allowableValues = { "대기업", "공기업", "공무원", "중견기업", "그외" })
	private List<String> companyList;
	@Schema(description = "참가비 정보 항목 id 리스트 (모임 비용이 있을 경우 설정될 수 있음)", nullable = true, example = "[1, 3]")
	private List<Integer> entreFeeInfoIdList;
	@Schema(description = "참가비 정보 기타 항목 string (모임 비용이 있을 경우 설정될 수 있음)", nullable = true, example = "장식 구매 비용")
	private String entreFeeInfoEtcString;
	@Schema(description = "테마 카테고리 id", nullable = false, example = "1")
	private Integer themeCategoryId;
	@Schema(description = "모임 참가시 필요한 질문(선착순 아닐 경우)", nullable = true, example = "자기소개 해주세요.")
	private String question;
	@Schema(description = "모임 헤더 이미지 url", nullable = true)
	private String headerImageUrl;
	@Schema(description = "참가 가능한 성별", nullable = false, example = "BOTH", allowableValues = { "BOTH", "MAN", "WOMAN" })
	private JoinGender joinGender;

}
