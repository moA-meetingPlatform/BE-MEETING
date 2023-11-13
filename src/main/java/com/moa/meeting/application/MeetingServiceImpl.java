package com.moa.meeting.application;


import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.MeetingStatus;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.infrastructure.MeetingRepository;
import com.moa.meeting.vo.response.MeetingSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

	private final MeetingRepository meetingRepository;
	private final ModelMapper modelMapper;

	@Transactional(readOnly = false)
	@Override
	public MeetingGetDto createMeeting(MeetingCreateDto meetingCreateDto) {

		Meeting meeting = Meeting.builder()
				.meetingTitle(meetingCreateDto.getMeetingTitle())
				.hostUserUuid(meetingCreateDto.getHostUserUuid())
				.meetingPlaceAddress(meetingCreateDto.getMeetingPlaceAddress())
				.meetingDescription(meetingCreateDto.getMeetingDescription())
				.meetingEntryFee(meetingCreateDto.getMeetingEntryFee())
				.meetingDatetime(meetingCreateDto.getMeetingDatetime())
				.firstComeFirstServed(meetingCreateDto.getFirstComeFirstServed())
				.onlineStatus(meetingCreateDto.getOnlineStatus())
				.maxParticipantsCount(meetingCreateDto.getMaxParticipantsCount())
				.currentParticipantsCount(1)
//				.maxAgeLimit(meetingCreateDto.getMaxAgeLimit())
//				.minAgeLimit(meetingCreateDto.getMinAgeLimit())
//				.canParticipateCompanyList(meetingCreateDto.getCanParticipateCompanyList())
				.entryFeeInformationList(meetingCreateDto.getEntryFeeInformationList())
				.entryFeeInfomationEtcString(meetingCreateDto.getEntryFeeInfomationEtcString())
				.meetingParticipationQuestion(meetingCreateDto.getMeetingParticipationQuestion())
				.meetingHeaderImageUrl(meetingCreateDto.getMeetingHeaderImageUrl())
				.meetingStatus(MeetingStatus.RECRUIT_IN_PROGRESS)
//				.canParticipateGender(meetingCreateDto.getCanParticipateGender())
				.build();
		meeting = meetingRepository.save(meeting);
		return modelMapper.map(meeting, MeetingGetDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<MeetingSimpleResponse> getMeetingsByIds(List<Long> ids) {
		List<Meeting> meetings = meetingRepository.findByIdIn(ids);
		return meetings.stream()
				.map(meeting -> new MeetingSimpleResponse(
						meeting.getId(),
						meeting.getMeetingTitle(),
						meeting.getHostUserUuid(),
						meeting.getMeetingPlaceAddress(),
						meeting.getMeetingDatetime(),
						meeting.getFirstComeFirstServed(),
						meeting.getOnlineStatus(),
						meeting.getMaxParticipantsCount(),
						meeting.getCurrentParticipantsCount(),
						meeting.getMeetingHeaderImageUrl(),
						meeting.getMeetingStatus().getTitle() // 여기서 getTitle을 호출하여 status의 title을 설정합니다.
				))
				.collect(Collectors.toList());
	}

}
