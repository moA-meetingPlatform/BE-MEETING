package com.moa.meeting.adaptor.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class MeetingController {

	@GetMapping("/test")
	public ResponseEntity<Map<?, ?>> test() {
		log.debug("this is meeting controller test");
		Map<String, String> map = Map.of("message", "meeting test");
		return ResponseEntity.ok(map);
	}
}
