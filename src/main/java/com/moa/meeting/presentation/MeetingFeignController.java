package com.moa.meeting.presentation;


import com.moa.global.vo.ApiResult;
import com.moa.meeting.application.MeetingFeignService;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.vo.request.MeetingCreateRequest;
import com.moa.meeting.vo.response.MeetingCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class MeetingFeignController {

	private final ModelMapper modelMapper;
	private final MeetingFeignService meetingFeignService;


	@Operation(summary = "모임 생성(no kafka, 동기 통신)", description = "모임 생성시 feign을 이용한 동기 통신하는 api")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = MeetingCreateResponse.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("/feign")
	public ResponseEntity<ApiResult<MeetingCreateResponse>> createMeetingWithFeign(@RequestBody MeetingCreateRequest request) {
		log.debug(request.toString());
		MeetingGetDto meetingGetDto = meetingFeignService.createMeetingWithFeign(
			modelMapper.map(request, MeetingCreateDto.class));// 모임 생성
		log.debug(meetingGetDto.toString());
		MeetingCreateResponse response = modelMapper.map(meetingGetDto, MeetingCreateResponse.class);// 모임 생성 후 반환
		return ResponseEntity.ok(ApiResult.ofSuccess(response));
	}

}
