package com.federation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CollectivityStats {
        private UUID collectivityId;
        private String collectivityName;
        private Long totalMembers;
        private Long activeMembers;
        private Long inactiveMembers;
        private BigDecimal totalPayments;
        private Long totalActivities;
        private Long mandatoryActivities;
        private Long exceptionalActivities;
        private Double averageAttendanceRate;
        private Map<String, BigDecimal> paymentsByMonth;
        private Map<String, Long> activitiesByMonth;
        private List<MemberStats> topContributors;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberStats {
        private UUID memberId;
        private String memberName;
        private Integer seniorityDays;
        private LocalDate joinDate;
        private BigDecimal totalPayments;
        private Long paymentsCount;
        private Long activitiesAttended;
        private Long activitiesMissed;
        private Double attendanceRate;
        private List<MandateHistory> mandateHistory;
        private Map<String, BigDecimal> paymentsByMonth;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MandateHistory {
        private String roleName;
        private LocalDate startDate;
        private LocalDate endDate;
        private Boolean isActive;
    }
}