package com.lsi.customer.Controller.Record;

import com.lsi.customer.Entity.Address;

public record CustomerResponse(
  String id,
  String firstName,
  String lastName,
  String email,
  Address address
) {
}
