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
        List<DeliveryDTO> listDeliveryDTO = deliveryMapper.toListDeliveryDTO(deliveryRepository.findAll());
        return listDeliveryDTO;
    }

    public DeliveryDTO getDelivery(Long id) {
        DeliveryDTO deliveryDTO = deliveryRepository.findById(id).map(deliveryMapper::toDeliveryDTO).orElseThrow(() ->
                new ResourceNotFoundException(DELIVERY_WAS_NOT_FOUND));
        return deliveryDTO;
    }
}
