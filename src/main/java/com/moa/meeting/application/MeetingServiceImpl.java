package com.moa.meeting.application;


import com.moa.global.config.exception.CustomException;
import com.moa.global.config.exception.ErrorCode;
import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.MeetingStatus;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.dto.MeetingSimpleGetDto;
import com.moa.meeting.infrastructure.mysql.MeetingRepository;
import com.moa.meeting.vo.response.MeetingSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

	private final MeetingRepository meetingRepository;
	private final ModelMapper modelMapper;


	@Override
	public MeetingGetDto createMeeting(MeetingCreateDto meetingCreateDto) {

		String CompanyListStr = null;
		String entryFeeInfomationIdListStr = null;

		//
		if (meetingCreateDto.getCanParticipateCompanyList() != null) {
			// 회사 그룹 List ->  Integer list to String, delemeter : ','
			CompanyListStr = meetingCreateDto.getCanParticipateCompanyList().toString().replaceAll(" ", "");   // 공백 제거
			CompanyListStr = CompanyListStr.substring(1, CompanyListStr.length() - 1);  // [ ] 제거
		}
		if (meetingCreateDto.getEntryFeeInfomationIdList() != null) {
			// 참가비 id List -> Integer list to String, delemeter : ','
			entryFeeInfomationIdListStr = meetingCreateDto.getEntryFeeInfomationIdList().toString().replaceAll(" ", "");
			entryFeeInfomationIdListStr = entryFeeInfomationIdListStr.substring(1, entryFeeInfomationIdListStr.length() - 1);
		}

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
			.maxAgeLimit(meetingCreateDto.getMaxAgeLimit())
			.minAgeLimit(meetingCreateDto.getMinAgeLimit())
			.canParticipateCompanyList(CompanyListStr)
			.entryFeeInfomationList(entryFeeInfomationIdListStr)
			.entryFeeInfomationEtcString(meetingCreateDto.getEntryFeeInfomationEtcString())
			.meetingParticipationQuestion(meetingCreateDto.getMeetingParticipationQuestion())
			.meetingHeaderImageUrl(meetingCreateDto.getMeetingHeaderImageUrl())
			.meetingStatus(MeetingStatus.RECRUIT_IN_PROGRESS)
			.canParticipateGender(meetingCreateDto.getCanParticipateGender())
			.build();

		meeting = meetingRepository.save(meeting);
		return modelMapper.map(meeting, MeetingGetDto.class);
	}


	@Override
	public MeetingSimpleGetDto getMeetingSimple(Long id) {
		Meeting meeting = meetingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE));
		return MeetingSimpleGetDto.fromEntity(meeting);
	}

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
