package com.moa.meeting.common;


import com.moa.meeting.config.exception.ErrorCode;


public record ApiResponse<T>(
	T result,
	Boolean isSuccess,
	String message

) {

	public static ApiResponse<Void> ofError(ErrorCode code) {
		return new ApiResponse<>(null, false, code.getDescription());
	}

	public static <T> ApiResponse<T> ofSuccess(T data) {
		return new ApiResponse<>(data, true, "success");
	}

}