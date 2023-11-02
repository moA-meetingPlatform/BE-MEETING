package com.moa.meeting.infrastructure.converter;


import com.moa.global.AbstractBaseEnumConverter;
import com.moa.meeting.domain.enums.CanParticipateGender;
import com.moa.meeting.domain.enums.MeetingStatus;


public class BaseEnumConverter {

	public static class CanParticipateGenderConverter extends AbstractBaseEnumConverter<CanParticipateGender, Character, String> {

		public CanParticipateGenderConverter() {
			super(CanParticipateGender.class);
		}

	}

	public static class MeetingStatusConverter extends AbstractBaseEnumConverter<MeetingStatus, String, String> {

		public MeetingStatusConverter() {
			super(MeetingStatus.class);
		}

	}

}
