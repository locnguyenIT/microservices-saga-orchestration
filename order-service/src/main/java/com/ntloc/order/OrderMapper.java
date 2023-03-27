package com.ntloc.order;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderDTO ordersDTO);

    List<Order> toListOrder(List<OrderDTO> listOrderDTO);

    OrderDTO toOrderDTO(Order order);

    List<OrderDTO> toListOrderDTO(List<Order> listOrder);
}
