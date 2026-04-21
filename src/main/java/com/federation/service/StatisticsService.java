package com.federation.service;

import com.federation.dto.response.StatisticsResponse;
import com.federation.entity.Collectivity;
import com.federation.entity.Mandate;
import com.federation.entity.Member;
import com.federation.enums.ActivityType;
import com.federation.enums.MemberStatus;
import com.federation.exception.ResourceNotFoundException;
import com.federation.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;
    private final ActivityRepository activityRepository;
    private final AttendanceRepository attendanceRepository;
    private final MandateRepository mandateRepository;

    @Transactional(readOnly = true)
    public StatisticsResponse.CollectivityStats getCollectivityStats(UUID collectivityId) {
        log.info("Calcul des statistiques pour la collectivité: {}", collectivityId);

        Collectivity collectivity = collectivityRepository.findById(collectivityId)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée: " + collectivityId));

        // Nombre total de membres
        Long totalMembers = memberRepository.countByCollectivityId(collectivityId);

        // Membres actifs et inactifs
        Long activeMembers = memberRepository.countByCollectivityIdAndStatus(collectivityId, MemberStatus.ACTIVE);
        Long inactiveMembers = totalMembers - activeMembers;

        // Total des paiements
        BigDecimal totalPayments = paymentRepository.sumPaymentsByCollectivityId(collectivityId);

        // Statistiques des activités
        Long totalActivities = activityRepository.countActivitiesByCollectivityId(collectivityId);
        Long mandatoryActivities = activityRepository.countByCollectivityIdAndType(collectivityId, ActivityType.MANDATORY);
        Long exceptionalActivities = totalActivities - mandatoryActivities;

        // Taux de présence moyen
        Double averageAttendanceRate = calculateAverageAttendanceRate(collectivityId);

        // Paiements par mois
        Map<String, BigDecimal> paymentsByMonth = getPaymentsByMonth(collectivityId);

        // Activités par mois
        Map<String, Long> activitiesByMonth = getActivitiesByMonth(collectivityId);

        // Top contributeurs
        List<StatisticsResponse.MemberStats> topContributors = getTopContributors(collectivityId, 5);

        return StatisticsResponse.CollectivityStats.builder()
                .collectivityId(collectivityId)
                .collectivityName(collectivity.getName())
                .totalMembers(totalMembers)
                .activeMembers(activeMembers)
                .inactiveMembers(inactiveMembers)
                .totalPayments(totalPayments != null ? totalPayments : BigDecimal.ZERO)
                .totalActivities(totalActivities)
                .mandatoryActivities(mandatoryActivities)
                .exceptionalActivities(exceptionalActivities)
                .averageAttendanceRate(averageAttendanceRate)
                .paymentsByMonth(paymentsByMonth)
                .activitiesByMonth(activitiesByMonth)
                .topContributors(topContributors)
                .build();
    }

    @Transactional(readOnly = true)
    public StatisticsResponse.MemberStats getMemberStats(UUID memberId) {
        log.info("Calcul des statistiques pour le membre: {}", memberId);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé: " + memberId));

        // Total des paiements
        BigDecimal totalPayments = paymentRepository.sumPaymentsByMemberId(memberId);

        // Nombre de paiements
        Long paymentsCount = paymentRepository.countPaymentsByMemberId(memberId);

        // Activités participées
        Long activitiesAttended = attendanceRepository.countAttendedActivitiesByMemberId(memberId);

        // Historique des mandats
        List<StatisticsResponse.MandateHistory> mandateHistory = getMandateHistory(memberId);

        // Taux de présence
        Double attendanceRate = calculateMemberAttendanceRate(memberId);

        // Paiements par mois
        Map<String, BigDecimal> paymentsByMonth = getMemberPaymentsByMonth(memberId);

        return StatisticsResponse.MemberStats.builder()
                .memberId(memberId)
                .memberName(member.getFirstName() + " " + member.getLastName())
                .seniorityDays(member.getSeniorityDays())
                .joinDate(member.getJoinDate())
                .totalPayments(totalPayments != null ? totalPayments : BigDecimal.ZERO)
                .paymentsCount(paymentsCount)
                .activitiesAttended(activitiesAttended)
                .activitiesMissed(0L) // À calculer si nécessaire
                .attendanceRate(attendanceRate)
                .mandateHistory(mandateHistory)
                .paymentsByMonth(paymentsByMonth)
                .build();
    }

    private Double calculateAverageAttendanceRate(UUID collectivityId) {
        List<Object[]> results = attendanceRepository.findAttendanceRatesByCollectivityId(collectivityId);
        if (results.isEmpty()) {
            return 0.0;
        }

        double totalRate = results.stream()
                .mapToDouble(row -> {
                    Long total = (Long) row[0];
                    Long present = (Long) row[1];
                    return total > 0 ? (present * 100.0) / total : 0.0;
                })
                .sum();

        return totalRate / results.size();
    }

    private Map<String, BigDecimal> getPaymentsByMonth(UUID collectivityId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return paymentRepository.findByCollectivityId(collectivityId).stream()
                .collect(Collectors.groupingBy(
                        p -> p.getPaymentDate().format(formatter),
                        Collectors.reducing(BigDecimal.ZERO, p -> p.getAmount(), BigDecimal::add)
                ));
    }

    private Map<String, Long> getActivitiesByMonth(UUID collectivityId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return activityRepository.findByCollectivityId(collectivityId).stream()
                .collect(Collectors.groupingBy(
                        a -> a.getActivityDate().format(formatter),
                        Collectors.counting()
                ));
    }

    private List<StatisticsResponse.MemberStats> getTopContributors(UUID collectivityId, int limit) {
        return memberRepository.findByCollectivityId(collectivityId).stream()
                .map(member -> getMemberStats(member.getId()))
                .sorted((a, b) -> b.getTotalPayments().compareTo(a.getTotalPayments()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private List<StatisticsResponse.MandateHistory> getMandateHistory(UUID memberId) {
        return mandateRepository.findByMemberId(memberId).stream()
                .map(mandate -> StatisticsResponse.MandateHistory.builder()
                        .roleName(mandate.getRole().getName())
                        .startDate(mandate.getStartDate())
                        .endDate(mandate.getEndDate())
                        .isActive(mandate.getIsActive())
                        .build())
                .collect(Collectors.toList());
    }

    private Double calculateMemberAttendanceRate(UUID memberId) {
        Long attended = attendanceRepository.countAttendedActivitiesByMemberId(memberId);
        Long total = attendanceRepository.countByMemberId(memberId);

        if (total == 0) {
            return 0.0;
        }

        return (attended * 100.0) / total;
    }

    private Map<String, BigDecimal> getMemberPaymentsByMonth(UUID memberId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return paymentRepository.findByMemberId(memberId).stream()
                .collect(Collectors.groupingBy(
                        p -> p.getPaymentDate().format(formatter),
                        Collectors.reducing(BigDecimal.ZERO, p -> p.getAmount(), BigDecimal::add)
                ));
    }
}