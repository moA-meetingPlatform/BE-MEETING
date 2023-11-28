package com.moa.meeting.client;


import com.moa.global.config.FeignConfig;
import com.moa.meeting.client.vo.ChatGPTRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "chatGptClient", url = "https://api.openai.com", configuration = FeignConfig.class)
public interface ChatGPTClient {

	@PostMapping("/v1/chat/completions")
	@Headers("Content-Type: application/json; charset=utf-8")
	String test(
		@RequestHeader("Authorization") String token,
		@RequestBody ChatGPTRequest chatGPTRequest
	);

}
