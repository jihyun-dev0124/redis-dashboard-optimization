package io.github.jihyundev.redis_dashboard_optimization.batch;

import io.github.jihyundev.redis_dashboard_optimization.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberBatchService {
    private final MemberService memberService;

    /**
     * 회원 대시보드 캐시 update
     */
    @CacheEvict(value = "membersDashboard", allEntries = true)
    @Scheduled(cron = "0 0 2 * * *")
    public void updateMemberDashboardCache() {
        log.info("Updating cache for members dashboard");
        memberService.findMemberSummaryByPeriod();
    }
}
