package com.moa.meeting.application;


import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;


public interface MeetingService {

	MeetingGetDto createMeeting(MeetingCreateDto meetingCreateDto);

}
