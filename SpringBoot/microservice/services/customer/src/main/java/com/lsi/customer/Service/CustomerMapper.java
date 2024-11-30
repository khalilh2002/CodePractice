package com.lsi.customer.Service;

import com.lsi.customer.Controller.Record.CustomerRequest;
import com.lsi.customer.Controller.Record.CustomerResponse;
import com.lsi.customer.Entity.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
  public Customer toCustomer(@Valid CustomerRequest request) {
    if (request == null) {
      return null;
    }
    return Customer
      .builder()
      .id(request.id())
      .firstName(request.firstName())
      .lastName(request.lastName())
      .email(request.email())
      .address(request.address())
      .build();
  }

  public CustomerResponse fromCustomer(Customer customer) {
    return new CustomerResponse(
      customer.getId(),
      customer.getFirstName(),
      customer.getLastName(),
      customer.getEmail(),
      customer.getAddress()
    );
  }
}
