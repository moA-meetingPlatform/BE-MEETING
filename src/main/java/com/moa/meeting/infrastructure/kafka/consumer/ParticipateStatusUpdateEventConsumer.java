package com.moa.meeting.infrastructure.kafka.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moa.global.config.exception.CustomException;
import com.moa.global.config.exception.ErrorCode;
import com.moa.meeting.domain.enums.ApplicationStatus;
import com.moa.meeting.dto.kafka.ParticipantApplicationUpdateEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipateStatusUpdateEventConsumer {

	private final ObjectMapper objectMapper;


	@KafkaListener(topics = "participate-update", groupId = "participate-update")
	public void consume(String message) {
		log.debug(String.format("Consumed message : %s", message));

		Map<Object, Object> map;

		try {
			map = objectMapper.readValue(message, Map.class);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException : {}", e.getMessage() + "\n" + message);
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		log.debug("여까진 됨");

		ParticipantApplicationUpdateEventDto dto = ParticipantApplicationUpdateEventDto.builder()
			.id(((Number) map.get("id")).longValue())
			.meetingId(((Number) map.get("meetingId")).longValue())
			.prevApplicationStatus(ApplicationStatus.valueOf(String.valueOf(map.get("prevApplicationStatus"))))
			.currentApplicationStatus(ApplicationStatus.valueOf(String.valueOf(map.get("currentApplicationStatus"))))
			.updateByHost(Boolean.getBoolean((String) map.get("updateByHost")))
			.build();

		log.debug("dto : {}", dto.toString());

	}

}
