package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Web {
  @GetMapping(path="/")
  public String test(){
    return "test";
  }
}
