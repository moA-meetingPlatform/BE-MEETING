package com.moa.meeting.adaptor.infrastructure.mysql.converter;


import com.moa.meeting.domain.enums.JoinGender;
import com.moa.meeting.domain.enums.MeetingStatus;


public class BaseEnumConverter {

	public static class JoinGenderConverter extends AbstractBaseEnumConverter<JoinGender, Character, String> {

		public JoinGenderConverter() {
			super(JoinGender.class);
		}

	}

	public static class MeetingStatusConverter extends AbstractBaseEnumConverter<MeetingStatus, String, String> {

		public MeetingStatusConverter() {
			super(MeetingStatus.class);
		}

	}

}
