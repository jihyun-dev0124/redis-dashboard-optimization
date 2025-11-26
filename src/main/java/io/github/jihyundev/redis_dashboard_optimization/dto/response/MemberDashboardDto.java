package io.github.jihyundev.redis_dashboard_optimization.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberDashboardDto {
    private Long totalMembers;
    private Long signupsToday;
    private Long signupsYesterday;

    private Long signupsThisWeek;//이번주
    private Long signups1WeekAgo;
    private Long signups2WeeksAgo;
    private Long signups3WeeksAgo;

    private Long signupsThisMonth;
    private Long signupsLastMonth;
    private Long signupsLast3Month;

    @QueryProjection
    public MemberDashboardDto(Long totalMembers, Long signupsToday, Long signupsYesterday, Long signupsThisWeek, Long signups1WeekAgo, Long signups2WeeksAgo, Long signups3WeeksAgo, Long signupsThisMonth, Long signupsLastMonth, Long signupsLast3Month) {
        this.totalMembers = totalMembers;
        this.signupsToday = signupsToday;
        this.signupsYesterday = signupsYesterday;
        this.signupsThisWeek = signupsThisWeek;
        this.signups1WeekAgo = signups1WeekAgo;
        this.signups2WeeksAgo = signups2WeeksAgo;
        this.signups3WeeksAgo = signups3WeeksAgo;
        this.signupsThisMonth = signupsThisMonth;
        this.signupsLastMonth = signupsLastMonth;
        this.signupsLast3Month = signupsLast3Month;
    }

    public MemberDashboardDto() {
        this.totalMembers = 0L;
        this.signupsToday = 0L;
        this.signupsYesterday = 0L;
        this.signupsThisWeek = 0L;
        this.signups1WeekAgo = 0L;
        this.signups2WeeksAgo = 0L;
        this.signups3WeeksAgo = 0L;
        this.signupsThisMonth = 0L;
        this.signupsLastMonth = 0L;
        this.signupsLast3Month = 0L;
    }
}
