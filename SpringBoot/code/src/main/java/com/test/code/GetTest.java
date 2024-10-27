package com.test.code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTest {
  @GetMapping(path = "/")
  public String index() {
    return "Hello World";
  }
}
