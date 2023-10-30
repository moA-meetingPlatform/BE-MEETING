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
			meeting.getTitle(),
			meeting.getHostUserUuid(),
			meeting.getMeetingAddress(),
			meeting.getDescription(),
			meeting.getEntryFee(),
			meeting.getMeetingDatetime(),
			meeting.getRefundPolicy(),
			meeting.getIsFcfs(),
			meeting.getIsOnline(),
			meeting.getMaxParticipantNum(),
			meeting.getMaxAge(),
			meeting.getMinAge(),
			meeting.getCompanyList(),
			meeting.getEntryFeeInfoIdList(),
			meeting.getEntryFeeInfoEtcString(),
			meeting.getThemeCategoryId(),
			meeting.getQuestion(),
			meeting.getHeaderImageUrl(),
			meeting.getMeetingStatus(),
			meeting.getJoinGender()
		));
		return meetingEntity.toDomain();
	}

}
