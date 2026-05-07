package com.federation.service;

import com.federation.dto.request.ActivityRequest;
import com.federation.dto.response.ActivityResponse;
import com.federation.entity.Activity;
import com.federation.entity.Collectivity;
import com.federation.exception.ResourceNotFoundException;
import com.federation.repository.ActivityRepository;
import com.federation.repository.AttendanceRepository;
import com.federation.repository.CollectivityRepository;
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
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final CollectivityRepository collectivityRepository;
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public ActivityResponse create(ActivityRequest request) {
        log.info("Création d'une activité: {}", request.getTitle());

        Collectivity collectivity = collectivityRepository.findById(request.getCollectivityId())
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée: " + request.getCollectivityId()));

        Activity activity = Activity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .activityDate(request.getActivityDate())
                .type(request.getType())
                .collectivity(collectivity)
                .build();

        Activity savedActivity = activityRepository.save(activity);
        log.info("Activité créée avec succès: ID={}", savedActivity.getId());

        return toResponse(savedActivity);
    }

    @Transactional(readOnly = true)
    public List<ActivityResponse> findAll() {
        return activityRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ActivityResponse findById(UUID id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activité non trouvée: " + id));
        return toResponse(activity);
    }

    @Transactional(readOnly = true)
    public List<ActivityResponse> findByCollectivityId(UUID collectivityId) {
        return activityRepository.findByCollectivityIdOrderByActivityDateDesc(collectivityId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ActivityResponse toResponse(Activity activity) {
        Long attendanceCount = attendanceRepository.countAttendancesByActivityId(activity.getId());
        Long presentCount = attendanceRepository.countPresentByActivityId(activity.getId());

        return ActivityResponse.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .description(activity.getDescription())
                .activityDate(activity.getActivityDate())
                .type(activity.getType())
                .collectivityId(activity.getCollectivity() != null ? activity.getCollectivity().getId() : null)
                .collectivityName(activity.getCollectivity() != null ? activity.getCollectivity().getName() : null)
                .attendanceCount(attendanceCount)
                .presentCount(presentCount)
                .build();
    }
}