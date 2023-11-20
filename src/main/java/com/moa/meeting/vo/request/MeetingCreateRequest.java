package com.moa.meeting.vo.request;


import com.moa.meeting.domain.enums.CanParticipateGender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Schema(description = "모임 생성 request")
@Getter
@ToString
public class MeetingCreateRequest {

	@Schema(description = "모임 제목", nullable = false, example = "할로윈 파티!")
	private String title;

	@Schema(description = "유저 uuid", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "a642406c-6e20-11ee-b962-0242ac120002")
	private UUID hostUserUuid;

	@Schema(description = "유저 닉네임", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "이모아")
	private String hostNickname;

	@Schema(description = "주소(오프라인인 경우)", nullable = true, example = "부산시 해운대구")
	private String placeAddress;

	@Schema(description = "모임 상세 설명", nullable = true, example = "할로윈 파티 오실래요?\n박쥐 쿠키, 호박타르트가 준비되어 있어요!!")
	private String description;

	@Schema(description = "모임 비용", nullable = false, example = "10000")
	private Integer entryFee;

	@Schema(description = "모임 시작 시간", nullable = false, example = "2021-10-31T19:30:00")
	private LocalDateTime meetingDatetime;

	@Schema(description = "선착순 여부", nullable = false, example = "true")    // 선착순 : true, 승인제 : false
	private Boolean firstComeFirstServed;

	@Schema(description = "온라인 여부", nullable = false, example = "false")    // 오프라인 : false, 온라인 : true
	private Boolean onlineStatus;

	@Schema(description = "참가 인원수", nullable = false, example = "10")
	private Integer maxParticipants;

	@Schema(description = "최대 나이", nullable = true, example = "30")
	private Integer maxAge;

	@Schema(description = "최소 나이", nullable = true, example = "25")
	private Integer minAge;

	@Schema(description = "참가 가능한 회사 그룹 리스트", nullable = true, example = "[MAJOR_COMPANY,MEDIUM_COMPANY]",
		allowableValues = { "MAJOR_COMPANY", "MAJOR_COMPANY_SUBSIDIARY", "MEDIUM_COMPANY", "PUBLIC_CORPORATION", "PUBLIC_INSTITUTION", "FOREIGN_COMPANY", "VENTURE_COMPANY", "SMALL_COMPANY" })
	private List<String> participateCompanies;

	@Schema(description = "참가비 정보 항목 리스트 (모임 비용이 있을 경우 설정될 수 있음)", nullable = true, example = "[CONTENTS, NO_SHOW]",
		allowableValues = { "CONTENTS", "HOST", "NO_SHOW", "SPACE", "MATERIAL", "REFRESHMENT", "PLATFORM" })
	private List<String> entryFeeInformations;

	@Schema(description = "참가비 정보 기타 항목 string (모임 비용이 있을 경우 설정될 수 있음)", nullable = true, example = "장식 구매 비용")
	private String entryFeeInfomationEtcString;

	@Schema(description = "모임 참가시 필요한 질문(선착순 아닐 경우)", nullable = true, example = "자기소개 해주세요.")
	private String participationQuestion;

	@Schema(description = "모임 헤더 이미지 url", nullable = true)
	private String headerImageUrl;

	@Schema(description = "참가 가능한 성별", nullable = false, example = "BOTH", allowableValues = { "BOTH", "MAN", "WOMAN" })
	private CanParticipateGender participateGender;

	@Schema(description = "모임 카테고리 id", nullable = false, example = "12")
	private Integer themeCategoryId;

}
