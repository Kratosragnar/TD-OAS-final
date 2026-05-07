package com.federation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponse {
    private UUID id;
    private UUID activityId;
    private String activityTitle;
    private UUID memberId;
    private String memberName;
    private Boolean present;
    private String justification;
}