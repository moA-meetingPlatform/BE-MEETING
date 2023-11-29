package com.moa.meeting.dto.kafka;


import com.moa.meeting.domain.enums.ApplicationStatus;
import lombok.*;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantApplicationUpdateEventDto {

	private long id;
	private long meetingId;
	private ApplicationStatus prevApplicationStatus;
	private ApplicationStatus currentApplicationStatus;
	private boolean updateByHost;

}