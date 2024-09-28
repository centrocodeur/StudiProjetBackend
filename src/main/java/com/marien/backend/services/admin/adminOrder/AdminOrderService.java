package com.marien.backend.services.admin.adminOrder;


import com.marien.backend.dto.AnalyticsResponse;
import com.marien.backend.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlaceOrder();

    OrderDto changeOrderStatus(Long orderId, String status);

    AnalyticsResponse calculateAnalytics();

}
