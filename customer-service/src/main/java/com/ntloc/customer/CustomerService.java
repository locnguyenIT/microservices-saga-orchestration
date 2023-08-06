package com.ntloc.customer;


import com.ntloc.coreapi.customer.command.CreateCustomerCommand;
import com.ntloc.coreapi.customer.model.CreateCustomerRequest;
import com.ntloc.customer.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.ntloc.customer.CustomerConstant.MessagesConstant.CUSTOMER_WAS_NOT_FOUND;


@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CommandGateway commandGateway;

    public List<CustomerDTO> getAllCustomer() {
        return customerMapper.toListCustomerDTO(customerRepository.findAll());

    }

    public CustomerDTO getCustomer(String id) {
        return customerRepository.findById(id).map(customerMapper::toCustomerDTO).orElseThrow(() ->
                new ResourceNotFoundException(CUSTOMER_WAS_NOT_FOUND));
    }

    public CompletableFuture<String> createCustomer(CreateCustomerRequest createCustomerRequest) {
        CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(UUID.randomUUID().toString(),
                createCustomerRequest.name(),
                createCustomerRequest.email(),
                createCustomerRequest.gender(),
                createCustomerRequest.money());
        return commandGateway.send(createCustomerCommand);
    }
}
