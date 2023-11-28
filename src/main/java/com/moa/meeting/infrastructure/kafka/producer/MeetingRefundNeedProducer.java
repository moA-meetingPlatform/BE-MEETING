package com.moa.meeting.infrastructure.kafka.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moa.meeting.dto.kafka.MeetingRefundNeedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingRefundNeedProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	private final ObjectMapper objectMapper;
	private final String TOPIC = "meeting-application-refund-data";


	public void send(MeetingRefundNeedDto dto) {
		log.debug(String.format("Produce dto : %s", dto));
		try {
			String message = objectMapper.writeValueAsString(dto);
			kafkaTemplate.send(TOPIC, message);
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException : {}", e.getMessage() + "\n" + dto.toString());
		}
	}

}
