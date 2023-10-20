package com.moa.meeting.adaptor.web.controller;


import com.moa.meeting.common.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class HealthCheckController {

	@GetMapping("/test")
	public ResponseEntity<ApiResult<String>> test() {
		return ResponseEntity.ok(ApiResult.ofSuccess("meeting test"));
	}

}
