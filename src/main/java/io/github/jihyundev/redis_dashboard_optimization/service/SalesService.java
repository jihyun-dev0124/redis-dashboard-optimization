package io.github.jihyundev.redis_dashboard_optimization.service;

import io.github.jihyundev.redis_dashboard_optimization.dto.request.SalesPeriodCondition;
import io.github.jihyundev.redis_dashboard_optimization.dto.response.SalesDashboardDto;
import io.github.jihyundev.redis_dashboard_optimization.repository.SalesDashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesDashboardRepository salesDashboardRepository;

    /**
     * 기간별 매출 합산 내역
     * @return
     */
    @Cacheable(value = "salesDashboard", key="'dashboard'")
    public SalesDashboardDto findSalesSummaryByPeriod() {
        SalesPeriodCondition salesPeriodCondition = new SalesPeriodCondition();
        SalesDashboardDto salesSummary = salesDashboardRepository.findSalesSummary(salesPeriodCondition);
        if(salesSummary == null) return new SalesDashboardDto();

        return salesSummary;
    }

}
