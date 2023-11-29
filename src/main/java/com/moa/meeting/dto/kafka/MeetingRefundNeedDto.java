package com.moa.meeting.dto.kafka;


import lombok.*;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRefundNeedDto {

	private long meetingId;
	private long participateId;
	private float refundPercentage;
	private int refundAmount;

}
