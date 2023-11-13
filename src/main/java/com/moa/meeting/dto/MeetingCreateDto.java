package com.moa.meeting.dto;


import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.domain.enums.CompanyCategory;
import com.moa.meeting.domain.enums.EntryFeeInformation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCreateDto {

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
	private List<CompanyCategory> canParticipateCompanyList;
	private List<EntryFeeInformation> entryFeeInformationList;
	private String entryFeeInfomationEtcString;
	private String meetingParticipationQuestion;
	private String meetingHeaderImageUrl;
	private CanParticipateGender canParticipateGender;
	
}
