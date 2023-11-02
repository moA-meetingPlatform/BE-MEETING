package com.moa.meeting.application.service;


import com.moa.meeting.application.ports.in.MeetingUseCase;
import com.moa.meeting.application.ports.out.dto.MeetingGetDto;
import com.moa.meeting.application.ports.out.port.MeetingPort;
import com.moa.meeting.common.ModelMapperBean;
import com.moa.meeting.domain.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MeetingService implements MeetingUseCase {

	private final MeetingPort meetingPort;
	private final ModelMapperBean modelMapperBean;


	@Override
	public MeetingGetDto createMeeting(MeetingCreateQuery meetingCreateQuery) {
		Meeting meeting = meetingPort.createMeeting(Meeting.saveMeeting(
			meetingCreateQuery.getMeetingTitle(),
			meetingCreateQuery.getHostUserUuid(),
			meetingCreateQuery.getMeetingPlaceAddress(),
			meetingCreateQuery.getMeetingDescription(),
			meetingCreateQuery.getMeetingEntryFee(),
			meetingCreateQuery.getMeetingDatetime(),
			meetingCreateQuery.getFirstComeFirstServed(),
			meetingCreateQuery.getOnlineStatus(),
			meetingCreateQuery.getMaxParticipantsCount(),
			meetingCreateQuery.getMaxAgeLimit(),
			meetingCreateQuery.getMinAgeLimit(),
			meetingCreateQuery.getCanParticipateCompanyList(),
			meetingCreateQuery.getEntryFeeInfomationIdList(),
			meetingCreateQuery.getEntryFeeInfomationEtcString(),
			meetingCreateQuery.getMeetingParticipationQuestion(),
			meetingCreateQuery.getMeetingHeaderImageUrl(),
			meetingCreateQuery.getCanParticipateGender()
		));
		return modelMapperBean.modelMapper().map(meeting, MeetingGetDto.class);
	}

}
