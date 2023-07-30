package com.ntloc.delivery;

import com.ntloc.delivery.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.delivery.DeliveryConstant.MessagesConstant.DELIVERY_WAS_NOT_FOUND;

@AllArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    public List<DeliveryDTO> getAllDelivery() {
        return deliveryMapper.toListDeliveryDTO(deliveryRepository.findAll());
    }

    public DeliveryDTO getDelivery(Long id) {
        return deliveryRepository.findById(id).map(deliveryMapper::toDeliveryDTO).orElseThrow(() ->
                new ResourceNotFoundException(DELIVERY_WAS_NOT_FOUND));
    }
}
