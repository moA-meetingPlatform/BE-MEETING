package com.moa.meeting.application;


import com.moa.meeting.domain.Meeting;
import com.moa.meeting.domain.enums.EntryFeeInformation;
import com.moa.meeting.dto.MeetingCreateDto;
import com.moa.meeting.dto.MeetingDetailGetDto;
import com.moa.meeting.dto.MeetingGetDto;
import com.moa.meeting.infrastructure.MeetingRepository;
import com.moa.meeting.vo.response.MeetingSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

	private final MeetingRepository meetingRepository;
	private final ModelMapper modelMapper;

	@Operation(summary = "모임 생성", description = "모임 생성")
	@Transactional(readOnly = false)
	@Override
	public MeetingGetDto createMeeting(MeetingCreateDto meetingCreateDto) {
		// 참가비 정보를 enum의 코드로 변환
		String entryFeeInformationsCodes = meetingCreateDto.getEntryFeeInformations().stream()
				.map(EntryFeeInformation::getCode)
				.map(Object::toString)
				.collect(Collectors.joining(", "));

		Meeting meeting = Meeting.builder()
				.title(meetingCreateDto.getTitle())	// 모임명
				.hostUserUuid(meetingCreateDto.getHostUserUuid())	// 모임장 UUID
				.hostNickname(meetingCreateDto.getHostNickname())	// 모임장 닉네임
				.placeAddress(meetingCreateDto.getPlaceAddress())	// 모임장소 주소
				.description(meetingCreateDto.getDescription())	// 모임설명
				.entryFee(meetingCreateDto.getEntryFee())	// 참가비
				.meetingDatetime(meetingCreateDto.getMeetingDatetime())	// 모임시간
				.firstComeFirstServed(meetingCreateDto.getFirstComeFirstServed())	// 선착순 여부 : true면 선착순, false면 승인제
				.onlineStatus(meetingCreateDto.getOnlineStatus())	// 온라인 여부 : true면 온라인, false면 오프라인
				.maxParticipants(meetingCreateDto.getMaxParticipants())// 최대 참가자 수
				.currentParticipants(1)	// 현재 참가자 수 : 모임 생성 시 모임장 1명이 참가한 것으로 시작
				.entryFeeInformations(entryFeeInformationsCodes)	// 참가비 정보
				.entryFeeInfomationEtcString(meetingCreateDto.getEntryFeeInfomationEtcString())	// 참가비 정보 기타
				.participationQuestion(meetingCreateDto.getParticipationQuestion())	// 참가 신청 시 질문 :승인제일경우에만 입력받음, 선착순일 경우 null
				.headerImageUrl(meetingCreateDto.getHeaderImageUrl())	// 모임 헤더 이미지
				.build();
		meeting = meetingRepository.save(meeting);
		return modelMapper.map(meeting, MeetingGetDto.class);
	}

	@Operation(summary = "모임 간단 조회", description = "모임 조회")
	@Transactional(readOnly = true)
	@Override
	public List<MeetingSimpleResponse> getMeetingsByIds(List<Long> ids) {
		List<Meeting> meetings = meetingRepository.findByIdIn(ids);	// id로 모임 조회
		LocalDateTime now = LocalDateTime.now();	// 현재 시간

		return meetings.stream()
				.map(meeting -> {
					String meetingStatus = determineMeetingStatus(meeting, now);	// 모임 상태
					return MeetingSimpleResponse.builder()
							.id(meeting.getId())	// 모임 id
							.title(meeting.getTitle())	// 모임명
							.hostUserUuid(meeting.getHostUserUuid())	// 모임장 UUID
							.hostNickname(meeting.getHostNickname())	// 모임장 닉네임
							.placeAddress(meeting.getPlaceAddress())	// 모임장소 주소
							.meetingDatetime(meeting.getMeetingDatetime())	// 모임시간
							.firstComeFirstServed(meeting.getFirstComeFirstServed() ? "승인제" : "선착순")
							// 선착순 여부 : true면 선착순, false면 승인제
							.onlineStatus(meeting.getOnlineStatus() ? "온라인" : "오프라인")
							// 온라인 여부 : true면 온라인, false면 오프라인
							.maxParticipants(meeting.getMaxParticipants())	// 최대 참가자 수
							.currentParticipants(meeting.getCurrentParticipants())	// 현재 참가자 수
							.meetingHeaderImageUrl(meeting.getHeaderImageUrl())	// 모임 헤더 이미지
							.meetingStatus(meetingStatus)	// 모임 상태
							.build();
				})
				// 모집중, 모집완료인 모임만 반환
				.filter(response -> "모집중".equals(response.getMeetingStatus()) || "모집완료".equals(response.getMeetingStatus()))
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public MeetingDetailGetDto getMeeting(Long id) {
		Optional<Meeting> meetingOptional = meetingRepository.findById(id);

		if (!meetingOptional.isPresent()) {
			throw new RuntimeException("Meeting not found with id: " + id);
		}

		LocalDateTime now = LocalDateTime.now();	// 현재 시간
		Meeting meeting = meetingOptional.get();	// 모임
		String meetingStatus = determineMeetingStatus(meeting, now);	// 모임 상태

		MeetingDetailGetDto.MeetingDetailGetDtoBuilder builder = MeetingDetailGetDto.builder()
				.title(meeting.getTitle())	// 모임명
				.hostUuid(meeting.getHostUserUuid())	// 모임장 UUID
				.hostNickname(meeting.getHostNickname())	// 모임장 닉네임
				.placeAddress(meeting.getPlaceAddress())	// 모임장소 주소
				.description(meeting.getDescription())	// 모임설명
				.meetingDatetime(meeting.getMeetingDatetime().toString())	// 모임시간
				.firstComeFirstServed(meeting.getFirstComeFirstServed() ? "승인제" : "선착순")	// 선착순 여부 : true면 선착순, false면 승인제
				.onlineStatus(meeting.getOnlineStatus() ? "온라인" : "오프라인")	// 온라인 여부 : true면 온라인, false면 오프라인
				.maxParticipants(meeting.getMaxParticipants())	// 최대 참가자 수
				.currentParticipants(meeting.getCurrentParticipants())	// 현재 참가자 수
				.headerImageUrl(meeting.getHeaderImageUrl())	// 모임 헤더 이미지
				.meetingStatus(meetingStatus);	// 모임 상태

		if (meeting.getEntryFee() != null) {	// 참가비가 있을 경우
			builder.entryFee(meeting.getEntryFee());
		}
		if (meeting.getEntryFeeInformations() != null) {	// 참가비 정보가 있을 경우
			List<String> titles = convertCodesToTitles(meeting.getEntryFeeInformations());
			builder.entryFeeInformations(titles);
		}
		if (meeting.getEntryFeeInfomationEtcString() != null) {	// 참가비 정보 기타가 있을 경우
			builder.entryFeeInfomationEtcString(meeting.getEntryFeeInfomationEtcString());
		}
		if (meeting.getParticipationQuestion() != null) {	// 참가 신청 시 질문이 있을 경우
			builder.participationQuestion(meeting.getParticipationQuestion());
		}

		return builder.build();
	}

	private List<String> convertCodesToTitles(String entryFeeInformations) {	// 참가비 정보 코드를 참가비 정보 제목으로 변환
		return Arrays.stream(entryFeeInformations.split(","))	// 쉼표로 구분하여 리스트로 변환
				.map(String::trim)
				.map(code -> {
					// Enum의 code를 사용하여 해당 Enum 찾기
					return Arrays.stream(EntryFeeInformation.values())
							.filter(e -> e.getCode().toString().equals(code))
							.findFirst()
							.map(EntryFeeInformation::getTitle)
							.orElse("Unknown"); // 존재하지 않는 코드에 대한 처리
				})
				.collect(Collectors.toList());
	}

	@Operation(summary = "모임 상태", description = "모집중, 모집완료, 모집취소, 모임종료, 삭제")
	private String determineMeetingStatus(Meeting meeting, LocalDateTime now) {
		LocalDateTime meetingDatetime = meeting.getMeetingDatetime();	// 모임시간
		int currentParticipants = meeting.getCurrentParticipants();	// 현재 참가자 수
		int maxParticipants = meeting.getMaxParticipants();	// 최대 참가자 수

		// 모집중인 모임의 경우 현재 참가자 수가 3명 이상이면서 최대 참가자 수보다 작을 경우
		if (meetingDatetime.isAfter(now) && currentParticipants >= 3 && currentParticipants < maxParticipants) {
			return "모집중";
		}
		// 모집중인 모임의 경우 현재 참가자 수가 3명 미만이면서 모임시간 3시간 전일 경우
		else if (meetingDatetime.minusHours(3).isBefore(now) && currentParticipants <= 2) {
			return "모임취소";
		}
		// 모집중인 모임의 경우 현재 참가자 수가 최대 참가자 수일 경우
		else if (meetingDatetime.isAfter(now) && currentParticipants == maxParticipants) {
			return "모집완료";
		}
		// 모집완료인 모임의 경우 모임시간이 현재 시간과 같을 경우
		else if (meetingDatetime.equals(now)) {
			return "모임시작";
		}
		// 모집완료인 모임의 경우 모임시간이 현재 시간보다 3시간 이전일 경우
		else if (meetingDatetime.plusHours(3).isBefore(now)) {
			return "모임종료";
		}
		return ""; // 기본값, 필요에 따라 조정 가능
	}



}
