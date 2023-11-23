package com.moa.meeting.dto.kafka;


import lombok.Getter;


@Getter
public class MeetingCancelEventDto {

	private long meetingId;
	private boolean updateByHost;
	private int entryFee;

}
