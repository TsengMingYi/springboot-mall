package com.kitezeng.springbootmall.service;

import com.kitezeng.springbootmall.dto.CreateOrderRequest;
import com.kitezeng.springbootmall.dto.OrderQueryParams;
import com.kitezeng.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    public Order getOrderById(Integer orderId);

    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    public List<Order> getOrders(OrderQueryParams orderQueryParams);

    public Integer countOrder(OrderQueryParams orderQueryParams);

    public void deleteOrderByOrderId(Integer orderId);
}
