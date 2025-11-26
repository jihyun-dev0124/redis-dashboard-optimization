package io.github.jihyundev.redis_dashboard_optimization.dto.response;

import io.github.jihyundev.redis_dashboard_optimization.domain.member.MemberStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberDto {
    private Long id;
    private String username;
    private String realName;
    private MemberStatus status;
    private String phone;
    private String email;
}
