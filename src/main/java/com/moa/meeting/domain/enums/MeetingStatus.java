package com.moa.meeting.domain.enums;


import com.moa.meeting.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum MeetingStatus implements BaseEnum<String, String> {
	RECRUIT_IN_PROGRESS("RI", "모집중"),
	RECRUIT_DONE("RD", "모집 완료"),
	MEETING_END("E", "종료"),
	MEETING_CANCEL("MC", "취소");

	private final String code;
	private final String title;

}
