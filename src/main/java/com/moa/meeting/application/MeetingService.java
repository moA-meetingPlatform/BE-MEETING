package com.moa.meeting.application;


import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.dto.MeetingSimpleGetDto;


public interface MeetingService {

	MeetingGetDto createMeeting(MeetingCreateDto meetingCreateDto);
	MeetingSimpleGetDto getMeetingSimple(Long id);

}
