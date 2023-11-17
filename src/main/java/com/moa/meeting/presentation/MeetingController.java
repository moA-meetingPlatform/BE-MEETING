package com.moa.meeting.presentation;


import com.moa.global.vo.ApiResult;
import com.moa.meeting.application.MeetingService;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingDetailGetDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.vo.request.MeetingCreateRequest;
import com.moa.meeting.vo.request.MeetingSimpleRequest;
import com.moa.meeting.vo.response.MeetingCreateResponse;
import com.moa.meeting.vo.response.MeetingDetailResponse;
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

import java.util.List;


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
		MeetingGetDto meetingGetDto = meetingService.createMeeting(
				modelMapper.map(request, MeetingCreateDto.class));// 모임 생성
		log.debug(meetingGetDto.toString());
		MeetingCreateResponse response = modelMapper.map(meetingGetDto, MeetingCreateResponse.class);// 모임 생성 후 반환
		return ResponseEntity.ok(ApiResult.ofSuccess(response));
	}

	@Operation(summary = "모임 간단 조회", description = "모임 조회")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK",
					content = @Content(schema = @Schema(implementation = MeetingSimpleResponse.class))),
			@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("/list")
	public ResponseEntity<ApiResult<List<MeetingSimpleResponse>>> getMeetings(@RequestBody MeetingSimpleRequest request) {
		List<MeetingSimpleResponse> meetings = meetingService.getMeetingsByIds(request.getIds());	// 모임 조회
		return ResponseEntity.ok(ApiResult.ofSuccess((meetings))); // 직접 리스트를 ResponseEntity 안에 포함시켜 반환
	}

	@Operation(summary = "모임 상세 조회", description = "모임 상세 조회")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK",
					content = @Content(schema = @Schema(implementation = MeetingDetailResponse.class))),
			@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ApiResult<MeetingDetailResponse>> getMeeting(@PathVariable Long id) {
		MeetingDetailGetDto meetingDetailGetDto = meetingService.getMeeting(id);	// 모임 조회
		MeetingDetailResponse response = modelMapper.map(meetingDetailGetDto, MeetingDetailResponse.class);	// 모임 상세 조회 후 반환
		return ResponseEntity.ok(ApiResult.ofSuccess(response));
	}

}