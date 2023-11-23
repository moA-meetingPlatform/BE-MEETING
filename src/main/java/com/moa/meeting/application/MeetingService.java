package com.moa.meeting.application;


import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingDetailGetDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.vo.response.MeetingSimpleResponse;

import java.util.List;


public interface MeetingService {

	MeetingGetDto createMeeting(MeetingCreateDto meetingCreateDto);	// 모임 생성

	List<MeetingSimpleResponse> getMeetingsByIds(List<Long> ids);	// 모임 간단 조회

	MeetingDetailGetDto getMeeting(Long id);	// 모임 상세 조회

	void increaseViewCount(Long id);
}
