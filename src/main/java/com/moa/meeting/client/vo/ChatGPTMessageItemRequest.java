package com.moa.meeting.client.vo;


import lombok.*;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTMessageItemRequest {

	private String role;
	private String content;

}
