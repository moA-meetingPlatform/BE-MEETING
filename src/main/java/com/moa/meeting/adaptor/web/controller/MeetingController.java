package com.moa.meeting.adaptor.web.controller;


import com.moa.meeting.adaptor.web.request.MeetingCreateReq;
import com.moa.meeting.adaptor.web.response.MeetingCreateRes;
import com.moa.meeting.application.ports.in.MeetingUseCase;
import com.moa.meeting.application.ports.out.dto.MeetingGetDto;
import com.moa.meeting.common.ApiResult;
import com.moa.meeting.common.ModelMapperBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class MeetingController {

	private final ModelMapperBean modelMapperBean;
	private final MeetingUseCase meetingUseCase;


	@Operation(summary = "모임 생성", description = "모임 생성")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = MeetingCreateRes.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("")
	public ResponseEntity<ApiResult<MeetingCreateRes>> createMeeting(@RequestBody MeetingCreateReq request) {
		log.debug(request.toString());
		UUID userUuid = UUID.fromString("a642406c-6e20-11ee-b962-0242ac120002"); // TODO: 로그인 구현 후 수정
		MeetingGetDto meetingGetDto = meetingUseCase.createMeeting(MeetingUseCase.MeetingCreateQuery.toQuery(request, userUuid));
		log.debug(meetingGetDto.toString());
		MeetingCreateRes response = modelMapperBean.modelMapper().map(meetingGetDto, MeetingCreateRes.class);
		return ResponseEntity.ok(ApiResult.ofSuccess(response));
	}

}