package com.gemini.userservice.service;

import com.gemini.userservice.dto.OrdersRequestDto;
import com.gemini.userservice.dto.OrdersResponseDto;


public interface OrdersService {
    OrdersResponseDto kakaoOrder(OrdersRequestDto requestDto);


}