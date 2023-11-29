package com.moa.meeting.application;


import com.moa.meeting.dto.kafka.ParticipantApplicationUpdateEventDto;


public interface KafkaMeetingService {

	void updateMeetingByParticipantApplicationUpdateEvent(ParticipantApplicationUpdateEventDto participantApplicationUpdateEventDto);

}
