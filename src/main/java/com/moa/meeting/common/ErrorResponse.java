package com.moa.meeting.common;


import com.moa.meeting.config.exception.ErrorCode;
import lombok.Getter;


@Getter
public class ErrorResponse {

	private final String message;


	public ErrorResponse(ErrorCode e) {
		this.message = e.getDescription();
	}

}