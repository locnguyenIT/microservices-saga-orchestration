package com.ntloc.customer;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerDTO customerDTO);

    List<Customer> toListCustomer(List<CustomerDTO> listCustomerDTO);

    CustomerDTO toCustomerDTO(Customer customer);

    List<CustomerDTO> toListCustomerDTO(List<Customer> listCustomer);

}


