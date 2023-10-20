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
			meetingCreateQuery.getTitle(),
			meetingCreateQuery.getHostUserUuid(),
			meetingCreateQuery.getMeetingAddress(),
			meetingCreateQuery.getDescription(),
			meetingCreateQuery.getEntryFee(),
			meetingCreateQuery.getMeetingDatetime(),
			meetingCreateQuery.getRefundPolicy(),
			meetingCreateQuery.getIsFcfs(),
			meetingCreateQuery.getIsOnline(),
			meetingCreateQuery.getMaxParticipantNum(),
			meetingCreateQuery.getMaxAge(),
			meetingCreateQuery.getMinAge(),
			meetingCreateQuery.getCompanyList(),
			meetingCreateQuery.getEntreFeeInfoIdList(),
			meetingCreateQuery.getEntreFeeInfoEtcString(),
			meetingCreateQuery.getThemeCategoryId(),
			meetingCreateQuery.getQuestion(),
			meetingCreateQuery.getHeaderImageUrl(),
			meetingCreateQuery.getJoinGender()
		));
		return modelMapperBean.modelMapper().map(meeting, MeetingGetDto.class);
	}

}
