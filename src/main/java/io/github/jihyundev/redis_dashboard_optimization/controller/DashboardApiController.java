package io.github.jihyundev.redis_dashboard_optimization.controller;

import io.github.jihyundev.redis_dashboard_optimization.dto.response.MemberDashboardDto;
import io.github.jihyundev.redis_dashboard_optimization.dto.response.SalesDashboardDto;
import io.github.jihyundev.redis_dashboard_optimization.service.MemberService;
import io.github.jihyundev.redis_dashboard_optimization.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardApiController {
    private final SalesService salesService;
    private final MemberService memberService;

    @GetMapping("/sales")
    public ResponseEntity<SalesDashboardDto> findSalesSummaryByPeriod() {
        SalesDashboardDto salesSummaryByPeriod = salesService.findSalesSummaryByPeriod();
        return ResponseEntity.ok(salesSummaryByPeriod);
    }

    @GetMapping("/members")
    public ResponseEntity<MemberDashboardDto> findMemberSummaryByPeriod() {
        MemberDashboardDto memberSummaryByPeriod = memberService.findMemberSummaryByPeriod();
        return ResponseEntity.ok(memberSummaryByPeriod);
    }
}
