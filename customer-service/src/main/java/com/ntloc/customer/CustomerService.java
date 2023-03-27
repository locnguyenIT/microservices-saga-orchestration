package com.ntloc.customer;


import com.ntloc.customer.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.MessagesConstant.CUSTOMER_WAS_NOT_FOUND;


@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomer() {
        return customerMapper.toListCustomerDTO(customerRepository.findAll());

    }

    public CustomerDTO getCustomer(Long id) {
        CustomerDTO customerDTO = customerRepository.findById(id).map(customerMapper::toCustomerDTO).orElseThrow(() ->
                new ResourceNotFoundException(CUSTOMER_WAS_NOT_FOUND));
        return customerDTO;
    }

}
