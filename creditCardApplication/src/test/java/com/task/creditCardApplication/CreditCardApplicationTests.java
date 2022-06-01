package com.task.creditCardApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan(basePackages="com.task.serviceImpl")
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
class CreditCardApplicationTests {

	@Test
	void contextLoads() {
	}

}
