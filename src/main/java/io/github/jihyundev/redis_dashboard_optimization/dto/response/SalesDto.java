package io.github.jihyundev.redis_dashboard_optimization.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class SalesDto {
    private Long id;
    private LocalDateTime settlementDatetime; // 정산 기준 날짜시간
    private int orderCount; //주문 수
    private int itemCount; // 품목 수
    private BigDecimal productPurchaseAmount; //상품 구매 금액
    private BigDecimal discountAmount; //할인 금액
    private BigDecimal couponDiscountAmount; //쿠폰 할인금액
    private BigDecimal actualPaymentAmount; //실제 결제금액
    private BigDecimal refundAmount; //환불 금액
    private BigDecimal netSalesAmount; //순매출
    private BigDecimal usedRewardPointAmount; // 적립금
    private BigDecimal usedDepositAmount; // 예치금
    private BigDecimal refundRewardPointAmount; // 환불 적립금
    private BigDecimal refundDepositAmount; // 환불 예치금

}
