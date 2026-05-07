package com.federation.service;

import com.federation.dto.request.AttendanceRequest;
import com.federation.dto.response.AttendanceResponse;
import com.federation.entity.Activity;
import com.federation.entity.Attendance;
import com.federation.entity.Member;
import com.federation.exception.BusinessException;
import com.federation.exception.ResourceNotFoundException;
import com.federation.repository.ActivityRepository;
import com.federation.repository.AttendanceRepository;
import com.federation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ActivityRepository activityRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public AttendanceResponse create(AttendanceRequest request) {
        log.info("Enregistrement de présence: activité={}, membre={}", request.getActivityId(), request.getMemberId());

        // Vérifier si la présence existe déjà
        if (attendanceRepository.existsByActivityIdAndMemberId(request.getActivityId(), request.getMemberId())) {
            throw new BusinessException("La présence a déjà été enregistrée pour ce membre à cette activité");
        }

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new ResourceNotFoundException("Activité non trouvée: " + request.getActivityId()));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + request.getMemberId()));

        // Vérifier que le membre appartient à la collectivité de l'activité
        if (member.getCollectivity() == null || activity.getCollectivity() == null ||
                !member.getCollectivity().getId().equals(activity.getCollectivity().getId())) {
            throw new BusinessException("Le membre n'appartient pas à la collectivité de cette activité");
        }

        Attendance attendance = Attendance.builder()
                .activity(activity)
                .member(member)
                .present(request.getPresent())
                .justification(request.getJustification())
                .build();

        Attendance savedAttendance = attendanceRepository.save(attendance);
        log.info("Présence enregistrée avec succès: ID={}", savedAttendance.getId());

        return toResponse(savedAttendance);
    }

    @Transactional(readOnly = true)
    public List<AttendanceResponse> findByActivityId(UUID activityId) {
        return attendanceRepository.findByActivityId(activityId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AttendanceResponse> findByMemberId(UUID memberId) {
        return attendanceRepository.findByMemberId(memberId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AttendanceResponse toResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .activityId(attendance.getActivity() != null ? attendance.getActivity().getId() : null)
                .activityTitle(attendance.getActivity() != null ? attendance.getActivity().getTitle() : null)
                .memberId(attendance.getMember() != null ? attendance.getMember().getId() : null)
                .memberName(attendance.getMember() != null ?
                        attendance.getMember().getFirstName() + " " + attendance.getMember().getLastName() : null)
                .present(attendance.getPresent())
                .justification(attendance.getJustification())
                .build();
    }
}