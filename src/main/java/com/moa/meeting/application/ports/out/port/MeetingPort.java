package com.moa.meeting.application.ports.out.port;


import com.moa.meeting.domain.Meeting;


public interface MeetingPort {

	Meeting createMeeting(Meeting meeting);

}
