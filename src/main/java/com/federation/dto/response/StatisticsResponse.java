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

<<<<<<< HEAD
/**
 * Classe conteneur pour les réponses de statistiques.
 * Ne pas instancier directement - utiliser les classes internes statiques.
 */
public final class StatisticsResponse {

    // Constructeur privé pour empêcher l'instanciation
=======
public final class StatisticsResponse {

>>>>>>> d7e79cd (Fourth commit)
    private StatisticsResponse() {
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée");
    }

<<<<<<< HEAD
    // ========== CLASSE INTERNE : CollectivityStats ==========
=======
>>>>>>> d7e79cd (Fourth commit)
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

<<<<<<< HEAD
    // ========== CLASSE INTERNE : MemberStats ==========
=======
>>>>>>> d7e79cd (Fourth commit)
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

<<<<<<< HEAD
    // ========== CLASSE INTERNE : MandateHistory ==========
=======
>>>>>>> d7e79cd (Fourth commit)
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