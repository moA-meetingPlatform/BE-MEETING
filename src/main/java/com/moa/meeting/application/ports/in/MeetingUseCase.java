package com.moa.meeting.application.ports.in;


import com.moa.meeting.adaptor.web.request.MeetingCreateReq;
import com.moa.meeting.application.ports.out.dto.MeetingGetDto;
import com.moa.meeting.domain.enums.JoinGender;
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

		private String title;
		private UUID hostUserUuid;
		private String meetingAddress;
		private String description;
		private Integer entryFee;
		private LocalDateTime meetingDatetime;
		private String refundPolicy;
		private Boolean isFcfs;
		private Boolean isOnline;
		private Integer maxParticipantNum;
		private Integer maxAge;
		private Integer minAge;
		private List<String> companyList;
		private List<Integer> entreFeeInfoIdList;
		private String entreFeeInfoEtcString;
		private Integer themeCategoryId;
		private String question;
		private String headerImageUrl;
		private JoinGender joinGender;


		public static MeetingCreateQuery toQuery(MeetingCreateReq request, UUID userUuid) {
			return MeetingCreateQuery.builder()
				.title(request.getTitle())
				.hostUserUuid(userUuid)
				.meetingAddress(request.getMeetingAddress())
				.description(request.getDescription())
				.entryFee(request.getEntryFee())
				.meetingDatetime(request.getMeetingDatetime())
				.refundPolicy(request.getRefundPolicy())
				.isFcfs(request.getIsFcfs())
				.isOnline(request.getIsOnline())
				.maxParticipantNum(request.getMaxParticipantNum())
				.maxAge(request.getMaxAge())
				.minAge(request.getMinAge())
				.companyList(request.getCompanyList())
				.entreFeeInfoIdList(request.getEntreFeeInfoIdList())
				.entreFeeInfoEtcString(request.getEntreFeeInfoEtcString())
				.themeCategoryId(request.getThemeCategoryId())
				.question(request.getQuestion())
				.headerImageUrl(request.getHeaderImageUrl())
				.joinGender(request.getJoinGender())
				.build();
		}

	}

}
