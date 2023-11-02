package com.moa.meeting.application.ports.in;


import com.moa.meeting.adaptor.web.request.MeetingCreateReq;
import com.moa.meeting.application.ports.out.dto.MeetingGetDto;
import com.moa.meeting.domain.enums.CanParticipateGender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface MeetingUseCase {

	MeetingGetDto createMeeting(MeetingCreateQuery meetingCreateQuery);
	@Getter
	@Builder
	class MeetingCreateQuery {

		private String meetingTitle;
		private UUID hostUserUuid;
		private String meetingPlaceAddress;
		private String meetingDescription;
		private Integer meetingEntryFee;
		private LocalDateTime meetingDatetime;
		private Boolean firstComeFirstServed;
		private Boolean onlineStatus;
		private Integer maxParticipantsCount;
		private Integer maxAgeLimit;
		private Integer minAgeLimit;
		private List<Integer> canParticipateCompanyList;
		private List<Integer> entryFeeInfomationIdList;
		private String entryFeeInfomationEtcString;
		private String meetingParticipationQuestion;
		private String meetingHeaderImageUrl;
		private CanParticipateGender canParticipateGender;


		public static MeetingCreateQuery toQuery(MeetingCreateReq request, UUID userUuid) {
			return MeetingCreateQuery.builder()
				.meetingTitle(request.getMeetingTitle())
				.hostUserUuid(userUuid)
				.meetingPlaceAddress(request.getMeetingPlaceAddress())
				.meetingDescription(request.getMeetingDescription())
				.meetingEntryFee(request.getMeetingEntryFee())
				.meetingDatetime(request.getMeetingDatetime())
				.firstComeFirstServed(request.getFirstComeFirstServed())
				.onlineStatus(request.getOnlineStatus())
				.maxParticipantsCount(request.getMaxParticipantsCount())
				.maxAgeLimit(request.getMaxAgeLimit())
				.minAgeLimit(request.getMinAgeLimit())
				.canParticipateCompanyList(request.getCanParticipateCompanyList())
				.entryFeeInfomationIdList(request.getEntryFeeInfomationIdList())
				.entryFeeInfomationEtcString(request.getEntryFeeInfomationEtcString())
				.meetingParticipationQuestion(request.getMeetingParticipationQuestion())
				.meetingHeaderImageUrl(request.getMeetingHeaderImageUrl())
				.canParticipateGender(request.getCanParticipateGender())
				.build();
		}

	}

}
