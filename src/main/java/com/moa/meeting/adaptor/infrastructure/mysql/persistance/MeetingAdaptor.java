package com.moa.meeting.adaptor.infrastructure.mysql.persistance;


import com.moa.meeting.adaptor.infrastructure.mysql.entity.MeetingEntity;
import com.moa.meeting.adaptor.infrastructure.mysql.repository.MeetingRepository;
import com.moa.meeting.application.ports.out.port.MeetingPort;
import com.moa.meeting.domain.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MeetingAdaptor implements MeetingPort {

	private final MeetingRepository meetingRepository;


	@Override
	public Meeting createMeeting(Meeting meeting) {
		MeetingEntity meetingEntity = meetingRepository.save(MeetingEntity.createMeeting(
			meeting.getMeetingTitle(),
			meeting.getHostUserUuid(),
			meeting.getMeetingPlaceAddress(),
			meeting.getMeetingDescription(),
			meeting.getMeetingEntryFee(),
			meeting.getMeetingDatetime(),
			meeting.getFirstComeFirstServed(),
			meeting.getOnlineStatus(),
			meeting.getMaxParticipantsCount(),
			meeting.getMaxAgeLimit(),
			meeting.getMinAgeLimit(),
			meeting.getCanParticipateCompanyList(),
			meeting.getEntryFeeInfomationIdList(),
			meeting.getEntryFeeInfomationEtcString(),
			meeting.getMeetingParticipationQuestion(),
			meeting.getMeetingHeaderImageUrl(),
			meeting.getMeetingStatus(),
			meeting.getCanParticipateGender()
		));
		return meetingEntity.toDomain();
	}

}
