package com.moa.meeting.presentation;


import com.moa.global.vo.ApiResult;
import com.moa.meeting.application.DescriptionGenerationService;
import com.moa.meeting.vo.request.DescreptionGenerationRequest;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/meeting/generate")
@Slf4j
@RequiredArgsConstructor
public class DescriptionGenerationController {

	private final DescriptionGenerationService descriptionGenerationService;


	@Operation(summary = "모임 설명 생성", description = "모임 설명 생성")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	})
	@PostMapping("")
	public ResponseEntity<ApiResult<String>> generateDescription(@RequestBody DescreptionGenerationRequest request) {
		log.debug(request.toString());
		String description = descriptionGenerationService.generateDescriptionTest(request);
		return ResponseEntity.ok(ApiResult.ofSuccess(description));
	}

}
