package com.moa.meeting.vo.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@ToString
public class DescreptionGenerationRequest {

	@Schema(description = "모임 제목", nullable = false, example = "할로윈 파티!")
	private String title;

	@Schema(description = "유저 닉네임", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED, example = "이모아")
	private String hostNickname;

	@Schema(description = "주소(오프라인인 경우)", nullable = true, example = "부산시 해운대구")
	private String placeAddress;

	@Schema(description = "모임 비용", nullable = false, example = "10000")
	private Integer entryFee;

	@Schema(description = "모임 시작 시간", nullable = false, example = "2021-10-31T19:30:00")
	private LocalDateTime meetingDatetime;

}
