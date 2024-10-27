package com.test.code;

import com.test.code.config.Pizza;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@SpringBootApplication
@Log
public class CodeApplication implements CommandLineRunner {
 /* private final Pizza pizza;
  CodeApplication(Pizza pizza) {
    this.pizza = pizza;
  }*/

  private final DataSource dataSource;
  public CodeApplication(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public static void main(String[] args) {
    SpringApplication.run(CodeApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    //log.info("Pizze name : "+pizza.getPizzaName()+" description : "+pizza.getPizzaDescription()+"number "+pizza.getNumberOfToppings());
    log.info("data source: " + dataSource.toString());
    JdbcTemplate restTemplate = new JdbcTemplate(dataSource);

  }
}
