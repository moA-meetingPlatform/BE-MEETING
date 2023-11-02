package com.moa.meeting.presentation;


import com.moa.global.vo.ApiResult;
import com.moa.meeting.application.MeetingService;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.dto.MeetingSimpleGetDto;
import com.moa.meeting.vo.request.MeetingCreateRequest;
import com.moa.meeting.vo.response.MeetingCreateResponse;
import com.moa.meeting.vo.response.MeetingSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class MeetingController {

	private final ModelMapper modelMapper;
	private final MeetingService meetingService;

	
	@Operation(summary = "모임 생성", description = "모임 생성")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = MeetingCreateResponse.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("")
	public ResponseEntity<ApiResult<MeetingCreateResponse>> createMeeting(@RequestBody MeetingCreateRequest request) {
		log.debug(request.toString());
		UUID userUuid = UUID.fromString("a642406c-6e20-11ee-b962-0242ac120002"); // TODO: 로그인 구현 후 수정
		MeetingGetDto meetingGetDto = meetingService.createMeeting(modelMapper.map(request, MeetingCreateDto.class));
		log.debug(meetingGetDto.toString());
		MeetingCreateResponse response = modelMapper.map(meetingGetDto, MeetingCreateResponse.class);
		return ResponseEntity.ok(ApiResult.ofSuccess(response));
	}


	@Operation(summary = "모임 간단 조회", description = "모임 조회")
	@GetMapping("/{meetingId}/simple")
	public ResponseEntity<ApiResult<MeetingSimpleResponse>> getMeetingSimple(@PathVariable("meetingId") Long meetingId) {
		MeetingSimpleGetDto meetingSimpleGetDto = meetingService.getMeetingSimple(meetingId);
		MeetingSimpleResponse response = modelMapper.map(meetingSimpleGetDto, MeetingSimpleResponse.class);
		return ResponseEntity.ok(ApiResult.ofSuccess(response));
	}

}