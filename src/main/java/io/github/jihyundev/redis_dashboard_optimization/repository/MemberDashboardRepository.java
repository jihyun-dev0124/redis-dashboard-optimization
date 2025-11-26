package io.github.jihyundev.redis_dashboard_optimization.repository;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.jihyundev.redis_dashboard_optimization.domain.member.MemberStatus;
import io.github.jihyundev.redis_dashboard_optimization.domain.member.QMember;
import io.github.jihyundev.redis_dashboard_optimization.dto.request.MemberPeriodCondition;
import io.github.jihyundev.redis_dashboard_optimization.dto.response.MemberDashboardDto;
import io.github.jihyundev.redis_dashboard_optimization.dto.response.QMemberDashboardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static io.github.jihyundev.redis_dashboard_optimization.domain.member.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberDashboardRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 기간별 회원가입 수
     * @param cond
     * @return
     */
    public MemberDashboardDto findSignupSummary(MemberPeriodCondition cond) {
        return queryFactory
                .select(new QMemberDashboardDto(
                        member.id.count(),
                        periodCount(member, cond.getTodayStart(), cond.getTodayEnd()),
                        periodCount(member, cond.getYesterdayStart(), cond.getYesterdayEnd()),
                        periodCount(member, cond.getThisWeekStart(), cond.getThisWeekEnd()),
                        periodCount(member, cond.getOneWeekAgoStart(), cond.getOneWeekAgoEnd()),
                        periodCount(member, cond.getTwoWeeksAgoStart(), cond.getTwoWeeksAgoEnd()),
                        periodCount(member, cond.getThreeWeeksAgoStart(), cond.getThreeWeeksAgoEnd()),
                        periodCount(member, cond.getThisMonthStart(), cond.getThisMonthEnd()),
                        periodCount(member, cond.getLastMonthStart(), cond.getLastMonthEnd()),
                        periodCount(member, cond.getLast3MonthsStart(), cond.getLast3MonthsEnd())
                ))
                .from(member)
                .where(member.status.eq(MemberStatus.ACTIVE))
                .fetchOne();
    }

    private NumberExpression<Long> periodCount(QMember member, LocalDateTime start, LocalDateTime end) {
        return new CaseBuilder()
                .when(member.createdAt.goe(start).and(member.createdAt.lt(end)))
                .then(1L)
                .otherwise(0L)
                .sum();
    }
}
