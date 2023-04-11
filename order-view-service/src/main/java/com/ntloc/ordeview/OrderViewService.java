package com.ntloc.ordeview;

import com.ntloc.ordeview.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderViewService {

    private final OrderViewRepository orderViewRepository;

    public Order getOrderByUserId(String id) {
        return orderViewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order was not found"));
    }
}
