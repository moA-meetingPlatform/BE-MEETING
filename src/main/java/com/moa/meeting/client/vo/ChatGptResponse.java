package com.moa.meeting.client.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@ToString
@Getter
@NoArgsConstructor
//ChatGPT 답변을 담을 DTO
public class ChatGptResponse {

	private String id;
	private String object;
	private long created;
	private String model;
	private Usage usage;
	private List<Choice> choices;

	@ToString
	@Getter
	@Setter
	public static class Usage {

		@JsonProperty("prompt_tokens")
		private int promptTokens;
		@JsonProperty("completion_tokens")
		private int completionTokens;
		@JsonProperty("total_tokens")
		private int totalTokens;

	}

	@ToString
	@Getter
	@Setter
	public static class Choice {

		private ChatGptMessageDto message;
		@JsonProperty("finish_reason")
		private String finishReason;
		private int index;

	}

}
