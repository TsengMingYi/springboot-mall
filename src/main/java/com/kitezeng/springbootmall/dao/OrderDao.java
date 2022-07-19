package com.kitezeng.springbootmall.dao;

import com.kitezeng.springbootmall.dto.OrderQueryParams;
import com.kitezeng.springbootmall.model.Order;
import com.kitezeng.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    public List<OrderItem> getOrderItemByOrderId(Integer orderId);

    public Order getOrderById(Integer orderId);

    public Integer createOrder(Integer userId, Integer totalAmount);

    public void createOrderItems(Integer orderId , List<OrderItem> orderItemList);

    public List<Order> getOrders(OrderQueryParams orderQueryParams);

    public Integer countOrder(OrderQueryParams orderQueryParams);
}
