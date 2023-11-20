package com.moa.meeting.infrastructure.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.dto.CategoryMeetingCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/meeting")
@Slf4j
@RequiredArgsConstructor
public class KafkaTestController {

	private final MeetingCreateProducer meetingCreateProducer;
	private final ObjectMapper objectMapper;


	@GetMapping("/kafka-test")
	public void test() throws JsonProcessingException {
		CategoryMeetingCreateDto dto = CategoryMeetingCreateDto.builder()
			.categoryMeetingId(1L)
			.enable(true)
			.maxAge(30)
			.minAge(22)
			.participateGender(CanParticipateGender.BOTH)
			.build();

		log.info("dto : {}", objectMapper.writeValueAsString(dto));
		meetingCreateProducer.sendCreateMeetingEvent(CategoryMeetingCreateDto.builder()
			.categoryMeetingId(1L)
			.enable(true)
			.maxAge(30)
			.minAge(22)
			.participateGender(CanParticipateGender.BOTH)
			.build());
	}

}
