package com.test.code.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pizza")
public class Pizza {
  private String pizzaName;
  private String pizzaDescription;
  private int numberOfToppings;


  public String getPizzaName() {
    return pizzaName;
  }

  public void setPizzaName(String pizzaName) {
    this.pizzaName = pizzaName;
  }

  public String getPizzaDescription() {
    return pizzaDescription;
  }

  public void setPizzaDescription(String pizzaDescription) {
    this.pizzaDescription = pizzaDescription;
  }

  public int getNumberOfToppings() {
    return numberOfToppings;
  }

  public void setNumberOfToppings(int numberOfToppings) {
    this.numberOfToppings = numberOfToppings;
  }


}
