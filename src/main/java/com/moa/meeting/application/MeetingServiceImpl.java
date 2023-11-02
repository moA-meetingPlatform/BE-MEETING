package com.moa.meeting.application;


import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.MeetingStatus;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.infrastructure.mysql.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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
			.currentParticipantsCount(0)
			.maxAgeLimit(meetingCreateDto.getMaxAgeLimit())
			.minAgeLimit(meetingCreateDto.getMinAgeLimit())
			.canParticipateCompanyList(CompanyListStr)
			.entryFeeInfomationIdList(entryFeeInfomationIdListStr)
			.entryFeeInfomationEtcString(meetingCreateDto.getEntryFeeInfomationEtcString())
			.meetingParticipationQuestion(meetingCreateDto.getMeetingParticipationQuestion())
			.meetingHeaderImageUrl(meetingCreateDto.getMeetingHeaderImageUrl())
			.meetingStatus(MeetingStatus.RECRUIT_IN_PROGRESS)
			.canParticipateGender(meetingCreateDto.getCanParticipateGender())
			.build();

		meeting = meetingRepository.save(meeting);
		return modelMapper.map(meeting, MeetingGetDto.class);
	}

}
