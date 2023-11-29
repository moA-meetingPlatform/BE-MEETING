package com.moa.meeting.infrastructure.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moa.global.config.exception.CustomException;
import com.moa.global.config.exception.ErrorCode;
import com.moa.meeting.dto.kafka.MeetingCancelEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingCancelProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	private final ObjectMapper objectMapper;

	private final String TOPIC = "meeting-cancel";


	public void send(MeetingCancelEventDto dto) {
		log.debug(String.format("Produce dto : %s", dto));
		try {
			String message = objectMapper.writeValueAsString(dto);
			kafkaTemplate.send(TOPIC, message);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException : {}", e.getMessage() + "\n" + dto.toString());
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}
	}

}
