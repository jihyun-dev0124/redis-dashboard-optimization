package io.github.jihyundev.redis_dashboard_optimization.repository;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.jihyundev.redis_dashboard_optimization.dto.request.SalesPeriodCondition;
import io.github.jihyundev.redis_dashboard_optimization.dto.response.QSalesDashboardDto;
import io.github.jihyundev.redis_dashboard_optimization.dto.response.SalesDashboardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.github.jihyundev.redis_dashboard_optimization.domain.sales.QSales.sales;


@Repository
@RequiredArgsConstructor
public class SalesDashboardRepository {
    private final JPAQueryFactory queryFactory;

    /**
     * 기간별 매출 합산 내역
     * @param cond
     * @return
     */
    public SalesDashboardDto findSalesSummary(SalesPeriodCondition cond) {
        //순매출 합산 기준 금액
        NumberExpression<BigDecimal> amount = sales.netSalesAmount;

        return queryFactory
                .select(new QSalesDashboardDto(
                        //1) totalSales - 전체 기간 합
                        amount.sum(),
                        //2) salesYesterday
                        new CaseBuilder()
                            .when(sales.settlementDatetime.goe(cond.getYesterdayStart())
                                    .and(sales.settlementDatetime.lt(cond.getYesterdayEnd())))
                            .then(amount)
                            .otherwise(BigDecimal.ZERO)
                            .sum(),
                        //3) salesThisWeek
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getThisWeekStart())
                                        .and(sales.settlementDatetime.lt(cond.getThisWeekEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        //4) sales1WeeksAgo
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getOneWeekAgoStart())
                                        .and(sales.settlementDatetime.lt(cond.getOneWeekAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        //5) sales2WeeksAgo
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getTwoWeeksAgoStart())
                                        .and(sales.settlementDatetime.lt(cond.getTwoWeeksAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        //6) sales3WeeksAgo
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getThreeWeeksAgoStart())
                                        .and(sales.settlementDatetime.lt(cond.getThreeWeeksAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        //7) sales4WeeksAgo
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getFourWeeksAgoStart())
                                                .and(sales.settlementDatetime.lt(cond.getFourWeeksAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getFiveWeeksAgoStart())
                                        .and(sales.settlementDatetime.lt(cond.getFiveWeeksAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getSixWeeksAgoStart())
                                        .and(sales.settlementDatetime.lt(cond.getSixWeeksAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getSevenWeeksAgoStart())
                                        .and(sales.settlementDatetime.lt(cond.getSevenWeeksAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getEightWeeksAgoStart())
                                        .and(sales.settlementDatetime.lt(cond.getEightWeeksAgoEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getThisMonthStart())
                                        .and(sales.settlementDatetime.lt(cond.getThisMonthEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getLastMonthStart())
                                        .and(sales.settlementDatetime.lt(cond.getLastMonthEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum(),
                        new CaseBuilder()
                                .when(sales.settlementDatetime.goe(cond.getLast3MonthsStart())
                                        .and(sales.settlementDatetime.lt(cond.getLast3MonthsEnd())))
                                .then(amount)
                                .otherwise(BigDecimal.ZERO)
                                .sum()
                    )
                ).from(sales)
                .fetchOne();
    }

}
