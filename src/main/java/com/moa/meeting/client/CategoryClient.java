package com.moa.meeting.client;


import com.moa.global.config.FeignConfig;
import com.moa.global.vo.ApiResult;
import com.moa.meeting.dto.CategoryMeetingCreateDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "categoryClient", url = "https://moamoa-backend.duckdns.org", configuration = FeignConfig.class)
public interface CategoryClient {

	@PostMapping("/api/v1/category/meeting")
	@Headers("Content-Type: application/json; charset=utf-8")
	ApiResult<Void> sendCreateMeetingEvent(
		@RequestBody CategoryMeetingCreateDto categoryMeetingCreateDto
	);

}
