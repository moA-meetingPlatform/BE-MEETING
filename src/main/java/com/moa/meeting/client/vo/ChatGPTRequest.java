package com.moa.meeting.client.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {

	private String model;
	private List<ChatGptMessageDto> messages;

	@JsonProperty("max_tokens")
	private Integer maxTokens;

	private Boolean stream;

}
