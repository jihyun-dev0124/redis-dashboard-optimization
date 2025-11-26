package io.github.jihyundev.redis_dashboard_optimization.dto.request;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MemberPeriodCondition {
    LocalDateTime todayStart;
    LocalDateTime todayEnd;

    LocalDateTime yesterdayStart;
    LocalDateTime yesterdayEnd;

    LocalDateTime thisWeekStart;
    LocalDateTime thisWeekEnd;
    LocalDateTime oneWeekAgoStart;
    LocalDateTime oneWeekAgoEnd;
    LocalDateTime twoWeeksAgoStart;
    LocalDateTime twoWeeksAgoEnd;
    LocalDateTime threeWeeksAgoStart;
    LocalDateTime threeWeeksAgoEnd;

    LocalDateTime thisMonthStart;
    LocalDateTime thisMonthEnd;
    LocalDateTime lastMonthStart;
    LocalDateTime lastMonthEnd;
    LocalDateTime last3MonthsStart; //지난 3개월간(이번달 제외)
    LocalDateTime last3MonthsEnd;

    public MemberPeriodCondition() {
        // 오늘 00:00
        LocalDate today = LocalDate.now();
        LocalDateTime todayStart = today.atStartOfDay();

        this.todayStart = todayStart;
        this.todayEnd = todayStart.plusDays(1);

        // === 어제 ===
        this.yesterdayStart = todayStart.minusDays(1);
        this.yesterdayEnd   = todayStart; // 오늘 00:00 (exclusive)

        // === 이번주 (월요일 00:00 ~ 다음주 월요일 00:00) ===
        LocalDate thisWeekMonday = today.with(DayOfWeek.MONDAY);
        this.thisWeekStart = thisWeekMonday.atStartOfDay();
        this.thisWeekEnd   = thisWeekStart.plusWeeks(1); // 다음주 월요일 00:00

        // === 1~3주 전 (각각 월~월, exclusive end) ===
        this.oneWeekAgoStart   = thisWeekStart.minusWeeks(1);
        this.oneWeekAgoEnd     = thisWeekStart;

        this.twoWeeksAgoStart  = thisWeekStart.minusWeeks(2);
        this.twoWeeksAgoEnd    = thisWeekStart.minusWeeks(1);

        this.threeWeeksAgoStart = thisWeekStart.minusWeeks(3);
        this.threeWeeksAgoEnd   = thisWeekStart.minusWeeks(2);

        // === 이번달 (1일 00:00 ~ 다음달 1일 00:00) ===
        this.thisMonthStart = today.withDayOfMonth(1).atStartOfDay();
        this.thisMonthEnd   = this.thisMonthStart.plusMonths(1); // 다음달 1일 00:00

        // === 지난달 (지난달 1일 00:00 ~ 이번달 1일 00:00) ===
        this.lastMonthStart = this.thisMonthStart.minusMonths(1);
        this.lastMonthEnd   = this.thisMonthStart;

        // === 지난 3개월간 (이번달 제외) ===
        this.last3MonthsStart = this.thisMonthStart.minusMonths(3);
        this.last3MonthsEnd   = this.thisMonthStart; // 이번달 1일 00:00까지 (exclusive)
    }
}
