package org.training.microservice.msorder.input.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.input.models.OrderQueryDto;
import org.training.microservice.msorder.services.models.Meal;
import org.training.microservice.msorder.services.models.Order;

@Mapper
public interface IOrderMapper {

    IOrderMapper ORDER_MAPPER = Mappers.getMapper(IOrderMapper.class);

    Order toOrder(OrderDto order);

    OrderDto toOrderDto(Order order);

    OrderQueryDto toOrderQueryDto(Order order);


}
