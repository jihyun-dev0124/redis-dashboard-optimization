package io.github.jihyundev.redis_dashboard_optimization.domain.sales;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_sales")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime settlementDatetime; // 정산 기준 날짜시간

    @Column(nullable = false)
    private int orderCount; //주문 수

    @Column(nullable = false)
    private int itemCount; // 품목 수

    @Column(nullable = false)
    private BigDecimal productPurchaseAmount; //상품 구매 금액

    @Column(nullable = false)
    private BigDecimal discountAmount; //할인 금액

    @Column(nullable = false)
    private BigDecimal couponDiscountAmount; //쿠폰 할인금액

    @Column(nullable = false)
    private BigDecimal actualPaymentAmount; //실제 결제금액

    @Column(nullable = false)
    private BigDecimal refundAmount; //환불 금액

    @Column(nullable = false)
    private BigDecimal netSalesAmount; //순매출

    @Column(nullable = false)
    private BigDecimal usedRewardPointAmount; // 적립금

    @Column(nullable = false)
    private BigDecimal usedDepositAmount; // 예치금

    @Column(nullable = false)
    private BigDecimal refundRewardPointAmount; // 환불 적립금

    @Column(nullable = false)
    private BigDecimal refund_deposit_amount; // 환불 예치금

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; //등록일자

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
