package com.gemini.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequestDto {
    private Integer orderStar;
    private String merchantUid;
    private String username;

}