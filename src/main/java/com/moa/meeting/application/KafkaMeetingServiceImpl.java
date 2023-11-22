package com.moa.meeting.application;


import com.moa.meeting.domain.Meeting;
import com.moa.meeting.dto.kafka.MeetingRefundNeedDto;
import com.moa.meeting.dto.kafka.ParticipantApplicationUpdateEventDto;
import com.moa.meeting.infrastructure.MeetingRepository;
import com.moa.meeting.infrastructure.kafka.producer.MeetingRefundNeedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class KafkaMeetingServiceImpl implements KafkaMeetingService {

	private static final float REFUND_PERCENT_ALL = 1.0f;
	private static final float REFUND_PERCENT_POLICY_1 = 0.5f;

	private final MeetingRepository meetingRepository;
	private final MeetingRefundNeedProducer meetingRefundNeedProducer;


	@Override
	@Transactional
	public void updateMeetingByParticipantApplicationUpdateEvent(ParticipantApplicationUpdateEventDto dto) {
		Meeting meeting = meetingRepository.findById(dto.getMeetingId()).orElseThrow(() -> new IllegalArgumentException("해당 모임이 존재하지 않습니다."));

		if (dto.isUpdateByHost()) { // 호스트가 참여신청을 승인/거절한 경우
			if (dto.getCurrentApplicationStatus().equals(dto.getPrevApplicationStatus())) { // 참여신청을 승인한 경우
				meeting.updateCurrentParticipants(1);
			} else { // 참여신청을 거절한 경우
				meeting.updateCurrentParticipants(-1);
				if (meeting.getEntryFee() != null && meeting.getEntryFee() > 0) {

					// 환불 정보 계산 (환불 비율 100%, 환불 금액은 참여비)
					float refundPercentage = 100.0f;
					int refundAmount = meeting.getEntryFee();

					// kafka로 환불 정보 데이터 전송
					meetingRefundNeedProducer.send(MeetingRefundNeedDto.builder()
						.meetingId(meeting.getId())
						.participateId(dto.getId())
						.refundPercentage(REFUND_PERCENT_ALL)
						.refundAmount(refundAmount)
						.build());
				}
			}
		} else { // 참여자가 참여신청을 취소한 경우
			LocalDateTime meetingDateTime = meeting.getMeetingDatetime();
			LocalDate meetingDate = meetingDateTime.toLocalDate(); // 모임 날짜를 LocalDate로 변환

			LocalDate currentDate = LocalDate.now(); // 현재 날짜

			LocalDate threeDaysBefore = meetingDate.minusDays(3); // 모임 날짜로부터 3일 전
			LocalDate twoDaysBefore = meetingDate.minusDays(2); // 모임 날짜로부터 2일 전

			// 모임 날짜 3일전 ~ 2일전 : 환불 비율 50%
			if (currentDate.isAfter(threeDaysBefore) && currentDate.isBefore(twoDaysBefore)) {
				// 환불 정보 계산 (환불 비율 50%, 환불 금액은 참여비)
				float refundPercentage = REFUND_PERCENT_POLICY_1;
				int refundAmount = (int) (meeting.getEntryFee() * refundPercentage);

				// kafka로 환불 정보 데이터 전송
				meetingRefundNeedProducer.send(MeetingRefundNeedDto.builder()
					.meetingId(meeting.getId())
					.participateId(dto.getId())
					.refundPercentage(refundPercentage)
					.refundAmount(refundAmount)
					.build());
			}
		}
	}

}
