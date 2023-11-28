package com.moa.meeting.client.vo;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ChatGptMessageDto implements Serializable {

	private String role;
	private String content;


	@Builder
	public ChatGptMessageDto(String role, String content) {
		this.role = role;
		this.content = content;
	}

}
