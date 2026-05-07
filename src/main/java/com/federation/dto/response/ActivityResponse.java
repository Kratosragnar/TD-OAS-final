package com.federation.dto.response;

import com.federation.enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime activityDate;
    private ActivityType type;
    private UUID collectivityId;
    private String collectivityName;
    private Long attendanceCount;
    private Long presentCount;
}