package io.github.jihyundev.redis_dashboard_optimization.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {

    @GetMapping("/sales")
    public String salesDashboard() {
        return "dashboard/sales";
    }

    @GetMapping("/members")
    public String memberDashboard() {
        return "dashboard/members";
    }


}
