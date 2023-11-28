package com.moa.meeting.application;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.moa.meeting.client.ChatGPTClient;
import com.moa.meeting.client.vo.ChatGPTRequest;
import com.moa.meeting.client.vo.ChatGptMessageDto;
import com.moa.meeting.vo.request.DescreptionGenerationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DescriptionGenerationServiceImpl implements DescriptionGenerationService {

	@Value("${OPENAI_API_TOKEN}")
	private String OPENAI_API_TOKEN;

	private final ChatGPTClient chatGPTClient;


	@Override
	public String generateDescriptionTest(DescreptionGenerationRequest request) {
		String token = "Bearer " + OPENAI_API_TOKEN;

		ChatGptMessageDto item1 = ChatGptMessageDto.builder()
			.role("system")
			.content(
				"너는 모임을 소개하는 글쓰기를 도와주는 사람이야. 많은 사람들이 모임에 관심을 가질 수 있도록 흥미로운 소개글을 쓸 수 있어.")
			.build();

		String gptRequest = String.format(
			"""
			모임 제목: %s
			모임 일시: %s
			모임 장소: %s
			모임 비용: %s
			모임 주최자: %s""",
			request.getTitle(),
			request.getMeetingDatetime(),
			request.getPlaceAddress(),
			request.getEntryFee(),
			request.getHostNickname()
		);

		ChatGptMessageDto item2 = ChatGptMessageDto.builder()
			.role("user")
			.content(gptRequest)
			.build();

		ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder()
			.model("gpt-3.5-turbo-16k")
			.messages(List.of(item1, item2))
			.maxTokens(800)
			.stream(true)
			.build();

		String result = chatGPTClient.test(token, chatGPTRequest);

		String[] split = result.split("\n\n");

		StringBuilder sb = new StringBuilder();
		for (String s : split) {
			if (!s.equals("data: [DONE]")) {
				s = s.replace("data: ", "");
				Gson gson = new Gson();
				JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
				JsonObject delta = jsonObject.getAsJsonArray("choices").get(0)
					.getAsJsonObject().getAsJsonObject("delta");

				if (delta.get("content") != null && !delta.get("content").isJsonNull()) {
					String content = delta.get("content").getAsString();
					sb.append(content);
				}
			}
		}
		return sb.toString();
	}

}
