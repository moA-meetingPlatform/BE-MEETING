package com.moa.meeting.domain.converter;

import com.moa.global.AbstractBaseEnumConverter;
import com.moa.meeting.domain.enums.CanParticipateGender;

public class CanParticipateGenderConverter extends AbstractBaseEnumConverter<CanParticipateGender, Character, String> {

    public CanParticipateGenderConverter() {
        super(CanParticipateGender.class);
    }
}
