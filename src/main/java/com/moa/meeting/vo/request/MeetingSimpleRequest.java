package com.moa.meeting.vo.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSimpleRequest {
    private List<Long> ids;
}
