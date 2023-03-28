package com.ntloc.delivery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryDTO> getAllDelivery() {
        return deliveryService.getAllDelivery();
    }

    @GetMapping(path = "/{id}")
    public DeliveryDTO getAllDelivery(@PathVariable("id") Long id) {
        return deliveryService.getDelivery(id);
    }
}
