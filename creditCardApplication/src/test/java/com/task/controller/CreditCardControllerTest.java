/**
 * 
 */
package com.task.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.dao.CreditCardDao;
import com.task.dao.UserDao;
import com.task.entity.CreditCard;
import com.task.entity.User;
import com.task.service.CreditCardService;

/**
 * @author kratika.jain
 *
 */
@WebMvcTest(CreditCardController.class)
@ComponentScan(basePackages = { "com.task.*" })
public class CreditCardControllerTest {
	@MockBean
	CreditCardService creditCardService;

	@MockBean
	CreditCardDao creditCardDao;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Autowired
	UserDao userDao;
	
	@TestConfiguration
	static class TestConfig {
		@Bean
		public UserDao userDao() {
			return Mockito.mock(UserDao.class);
		}
	}

	@BeforeEach
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(new MockMvcConfigurer() {
			@Override
			public RequestPostProcessor beforeMockMvcCreated(ConfigurableMockMvcBuilder<?> builder,
					WebApplicationContext context) {

				return (r) -> {
					r.addHeader("Authorizarion", "Bearer 34567865432kuhgfcv");
					return r;
				};
			}
		}).build();

	}

	@Test
	public void fetchCreditCardDetails_success() throws Exception {

		List<CreditCard> listOfCards = new ArrayList<CreditCard>();
		listOfCards.add(new CreditCard(1, "79927398713", LocalDate.now(), 2000.00, "VISA", new User()));
		when(creditCardService.fetchCCByUser(any())).thenReturn(listOfCards);
		mockMvc.perform(MockMvcRequestBuilders.get("/creditcard/getAll"))
				.andExpect(status().isOk());
		 
	}
	
	@Test
	public void fetchCreditCardDetailsOnUser_BadRequest() throws Exception {

		List<CreditCard> listOfCards = new ArrayList<CreditCard>();
		listOfCards.add(new CreditCard(1, "79927398713", LocalDate.now(), 2000.00, "VISA", new User()));
		when(creditCardService.fetchCCByUser(any())).thenReturn(listOfCards);
		mockMvc.perform(MockMvcRequestBuilders.get("/creditcard/get"))
				.andExpect(status().is4xxClientError());
		 
	}
	
	@Test
	public void saveCreditCardDetails_success() throws Exception {
		CreditCard creditCard= new CreditCard(1, "79927398713",null, 2000.00, "VISA", new User());
		when(creditCardService.saveCard(any(),any())).thenReturn(creditCard);
				 mockMvc.perform(MockMvcRequestBuilders.post("/creditcard/save")
			                .contentType(MediaType.APPLICATION_JSON)
			                .content(asJsonString(creditCard)))
			                .andExpect(status().isCreated())
			                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

		 
	}

	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
