package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.model.Order;

import java.util.List;

public interface IOrderService {
    Order insert(OrderDTO orderDTO) throws Exception;

    List<Order> getAll();

    Order getById(long id);


    Order updateById(long id, OrderDTO orderDTO) ;

    String deleteById(long orderId, long userId);
}
