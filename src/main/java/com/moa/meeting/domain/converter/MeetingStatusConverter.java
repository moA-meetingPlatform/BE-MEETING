package com.moa.meeting.domain.converter;

import com.moa.global.AbstractBaseEnumConverter;
import com.moa.meeting.domain.enums.MeetingStatus;

public class MeetingStatusConverter extends AbstractBaseEnumConverter<MeetingStatus, String, String> {

    public MeetingStatusConverter() {
        super(MeetingStatus.class);
    }
}