package com.moa.meeting.adaptor.web.controller;


import com.moa.meeting.adaptor.web.request.MeetingCreateReq;
import com.moa.meeting.common.ApiResult;
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


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class MeetingController {

	@Operation(summary = "모임 생성", description = "모임 생성")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
			content = @Content(schema = @Schema(implementation = String.class))),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("")
	public ResponseEntity<ApiResult<String>> createMeeting(@RequestBody MeetingCreateReq request) {
		log.debug(request.toString());
		return ResponseEntity.ok(ApiResult.ofSuccess("success"));
	}

}
