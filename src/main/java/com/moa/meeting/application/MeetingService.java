package com.moa.meeting.application;


import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.dto.MeetingSimpleGetDto;
import com.moa.meeting.vo.response.MeetingSimpleResponse;

import java.util.List;


public interface MeetingService {

	MeetingGetDto createMeeting(MeetingCreateDto meetingCreateDto);
	MeetingSimpleGetDto getMeetingSimple(Long id);

	List<MeetingSimpleResponse> getMeetingsByIds(List<Long> ids);
}
