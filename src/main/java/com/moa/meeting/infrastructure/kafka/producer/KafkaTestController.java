package com.moa.meeting.infrastructure.kafka.producer;


import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.dto.CategoryMeetingGetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class KafkaTestController {

	private final MeetingCreateProducer meetingCreateProducer;


	@RequestMapping("/kafka-test")
	public void test() {
		meetingCreateProducer.sendCreateMeetingEvent(CategoryMeetingGetDto.builder()
			.categoryMeetingId(1L)
			.enable(true)
			.maxAge(30)
			.minAge(22)
			.participateGender(CanParticipateGender.BOTH)
			.build());
	}

}
