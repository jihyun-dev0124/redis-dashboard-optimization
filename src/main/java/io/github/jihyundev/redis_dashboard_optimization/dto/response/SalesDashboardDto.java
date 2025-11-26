package io.github.jihyundev.redis_dashboard_optimization.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalesDashboardDto {
    private BigDecimal totalSales;     //총 매출액
    private BigDecimal salesYesterday; //어제 매출액
    private BigDecimal salesThisWeek;  //이번주 매출액

    private BigDecimal sales1WeeksAgo; //1주전 매출액
    private BigDecimal sales2WeeksAgo; //2주전 매출액
    private BigDecimal sales3WeeksAgo; //3주전 매출액
    private BigDecimal sales4WeeksAgo; //4주전 매출액
    private BigDecimal sales5WeeksAgo; //5주전 매출액
    private BigDecimal sales6WeeksAgo; //6주전 매출액
    private BigDecimal sales7WeeksAgo; //7주전 매출액
    private BigDecimal sales8WeeksAgo; //8주전 매출액

    private BigDecimal salesThisMonth; //이번달 매출액
    private BigDecimal salesLastMonth; //저번달 매출액
    private BigDecimal salesLast3Months; //지난 3개월간 매출액

    @QueryProjection
    public SalesDashboardDto(BigDecimal totalSales, BigDecimal salesYesterday, BigDecimal salesThisWeek, BigDecimal sales1WeeksAgo, BigDecimal sales2WeeksAgo, BigDecimal sales3WeeksAgo, BigDecimal sales4WeeksAgo, BigDecimal sales5WeeksAgo, BigDecimal sales6WeeksAgo, BigDecimal sales7WeeksAgo, BigDecimal sales8WeeksAgo, BigDecimal salesThisMonth, BigDecimal salesLastMonth, BigDecimal salesLast3Months) {
        this.totalSales = totalSales;
        this.salesYesterday = salesYesterday;
        this.salesThisWeek = salesThisWeek;
        this.sales1WeeksAgo = sales1WeeksAgo;
        this.sales2WeeksAgo = sales2WeeksAgo;
        this.sales3WeeksAgo = sales3WeeksAgo;
        this.sales4WeeksAgo = sales4WeeksAgo;
        this.sales5WeeksAgo = sales5WeeksAgo;
        this.sales6WeeksAgo = sales6WeeksAgo;
        this.sales7WeeksAgo = sales7WeeksAgo;
        this.sales8WeeksAgo = sales8WeeksAgo;
        this.salesThisMonth = salesThisMonth;
        this.salesLastMonth = salesLastMonth;
        this.salesLast3Months = salesLast3Months;
    }

    public SalesDashboardDto() {
        this.totalSales = BigDecimal.ZERO;
        this.salesYesterday = BigDecimal.ZERO;
        this.salesThisWeek = BigDecimal.ZERO;
        this.sales1WeeksAgo = BigDecimal.ZERO;
        this.sales2WeeksAgo = BigDecimal.ZERO;
        this.sales3WeeksAgo = BigDecimal.ZERO;
        this.sales4WeeksAgo = BigDecimal.ZERO;
        this.sales5WeeksAgo = BigDecimal.ZERO;
        this.sales6WeeksAgo = BigDecimal.ZERO;
        this.sales7WeeksAgo = BigDecimal.ZERO;
        this.sales8WeeksAgo = BigDecimal.ZERO;
        this.salesThisMonth = BigDecimal.ZERO;
        this.salesLastMonth = BigDecimal.ZERO;
        this.salesLast3Months = BigDecimal.ZERO;
    }
}
