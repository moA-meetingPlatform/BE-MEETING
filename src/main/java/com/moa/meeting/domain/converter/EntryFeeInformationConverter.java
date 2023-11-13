package com.moa.meeting.domain.converter;

import com.moa.meeting.domain.enums.EntryFeeInformation;
import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntryFeeInformationConverter implements AttributeConverter<List<EntryFeeInformation>, String> {

    @Override
    public String convertToDatabaseColumn(List<EntryFeeInformation> attribute) {
        //ex) "C,H,N"
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (EntryFeeInformation entry : attribute) {
            sb.append(entry.getCode()); // 열거형의 code 값을 사용하거나 필요한 속성 선택
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1); // 마지막 쉼표 제거
        return sb.toString();
    }

    // 문자열을 파싱하는 로직 ex)"C,H,N"을 열거형 값 리스트로 변환
    @Override
    public List<EntryFeeInformation> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        }
        String[] codes = dbData.split(",");
        List<EntryFeeInformation> result = new ArrayList<>();
        for (String code : codes) {
            EntryFeeInformation entry = EntryFeeInformation.valueOf(code); // 열거형의 코드를 사용하여 열거형 값 얻음
            result.add(entry);
        }
        return result;
    }
}
