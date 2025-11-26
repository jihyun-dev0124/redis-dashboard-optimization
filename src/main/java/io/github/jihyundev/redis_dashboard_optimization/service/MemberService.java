package io.github.jihyundev.redis_dashboard_optimization.service;

import io.github.jihyundev.redis_dashboard_optimization.dto.request.MemberPeriodCondition;
import io.github.jihyundev.redis_dashboard_optimization.dto.response.MemberDashboardDto;
import io.github.jihyundev.redis_dashboard_optimization.repository.MemberDashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDashboardRepository memberDashboardRepository;

    /**
     * 기간별 회원가입 수 내역
     * @return
     */
    @Cacheable(value = "membersDashboard", key = "'dashboard'")
    public MemberDashboardDto findMemberSummaryByPeriod() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("기간별 회원가입수  합산 내역");

        MemberPeriodCondition memberPeriodCondition = new MemberPeriodCondition();
        MemberDashboardDto memberSummary = memberDashboardRepository.findSignupSummary(memberPeriodCondition);
        if(memberSummary == null) return new MemberDashboardDto();

        stopWatch.stop();
        log.info("findSalesSummaryByPeriod timings={}", stopWatch.prettyPrint());
        return memberSummary;
    }

}
