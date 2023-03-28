package com.ntloc.delivery;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    Delivery toDelivery(DeliveryDTO DeliveryDTO);

    List<Delivery> toListDelivery(List<DeliveryDTO> listDeliveryDTO);

    DeliveryDTO toDeliveryDTO(Delivery Delivery);

    List<DeliveryDTO> toListDeliveryDTO(List<Delivery> listDelivery);

}


