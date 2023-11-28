package com.moa.meeting.application;


import com.moa.meeting.client.CategoryClient;
import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.EntryFeeInformation;
import com.moa.meeting.dto.CategoryMeetingCreateDto;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.infrastructure.MeetingRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class MeetingFeignService {

	private final ModelMapper modelMapper;
	private final MeetingRepository meetingRepository;

	private final CategoryClient categoryClient;


	@Operation(summary = "모임 생성", description = "모임 생성")
	@Transactional(readOnly = false)
	public MeetingGetDto createMeetingWithFeign(MeetingCreateDto meetingCreateDto) {
		// 참가비 정보를 enum의 코드로 변환
		String entryFeeInformationsCodes = meetingCreateDto.getEntryFeeInformations().stream()
			.map(EntryFeeInformation::getCode)
			.map(Object::toString)
			.collect(Collectors.joining(", "));

		Meeting meeting = Meeting.builder()
			.title(meetingCreateDto.getTitle())    // 모임명
			.hostUserUuid(meetingCreateDto.getHostUserUuid())    // 모임장 UUID
			.hostNickname(meetingCreateDto.getHostNickname())    // 모임장 닉네임
			.placeAddress(meetingCreateDto.getPlaceAddress())    // 모임장소 주소
			.description(meetingCreateDto.getDescription())    // 모임설명
			.entryFee(meetingCreateDto.getEntryFee())    // 참가비
			.meetingDatetime(meetingCreateDto.getMeetingDatetime())    // 모임시간
			.firstComeFirstServed(meetingCreateDto.getFirstComeFirstServed())    // 선착순 여부 : true면 선착순, false면 승인제
			.onlineStatus(meetingCreateDto.getOnlineStatus())    // 온라인 여부 : true면 온라인, false면 오프라인
			.maxParticipants(meetingCreateDto.getMaxParticipants())// 최대 참가자 수
			.currentParticipants(1)    // 현재 참가자 수 : 모임 생성 시 모임장 1명이 참가한 것으로 시작
			.entryFeeInformations(entryFeeInformationsCodes)    // 참가비 정보
			.entryFeeInfomationEtcString(meetingCreateDto.getEntryFeeInfomationEtcString())    // 참가비 정보 기타
			.participationQuestion(meetingCreateDto.getParticipationQuestion())    // 참가 신청 시 질문 :승인제일경우에만 입력받음, 선착순일 경우 null
			.headerImageUrl(meetingCreateDto.getHeaderImageUrl())    // 모임 헤더 이미지
			.build();
		meeting = meetingRepository.save(meeting);

		// 참여가능한 기업 리스트를 문자열로 변환
		String participateCompaniesStr = String.join(", ", meetingCreateDto.getParticipateCompanies());

		// 모임 생성 후 Feign으로 모임 생성 이벤트 전송
		// 모임 생성 이벤트를 받은 카테고리 서비스에서는 모임 카테고리 정보 저장
		CategoryMeetingCreateDto categoryMeetingCreateDto = CategoryMeetingCreateDto.builder()
			.categoryMeetingId(meeting.getId())
			.subCategoryId(meetingCreateDto.getThemeCategoryId())
			.maxAge(meetingCreateDto.getMaxAge())
			.minAge(meetingCreateDto.getMinAge())
			.participateGender(meetingCreateDto.getParticipateGender())
			.participateCompanies(participateCompaniesStr)
			.build();

		try {
			categoryClient.sendCreateMeetingEvent(categoryMeetingCreateDto);
		} catch (Exception e) {
			log.error("카테고리 서비스로 모임 생성 이벤트 전송 실패");
		}

		return modelMapper.map(meeting, MeetingGetDto.class);
	}

}
