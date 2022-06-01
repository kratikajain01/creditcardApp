/**
 * 
 */
package com.creditAppUi.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.creditAppUi.entity.CreditCard;
import com.creditAppUi.exception.RestTemplateResponseErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author kratika.jain
 *
 */

@Controller
public class UserCreditCardController {

	@Value("${credirCardUrl}")
	String credirCardUrl;

	@Autowired
	ObjectFactory<HttpSession> httpSessionFactory;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public UserCreditCardController(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/getCards")
	public String getAllCards(CreditCard creditCard, String token, Model model) {

		ResponseEntity<List<CreditCard>> listOfCreditCards = restTemplate.exchange(credirCardUrl + "/getAll",
				HttpMethod.GET, new HttpEntity(creditCard, setRequestHeader(getSession())), new ParameterizedTypeReference<List<CreditCard>>() {
				});
		model.addAttribute("listOfCreditCards", listOfCreditCards.getBody());
		return "showCreditCards";
	}

	@GetMapping("/addCard")
	public String navigate(CreditCard creditCard, String token) {
		return "add-user";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid CreditCard creditCard, BindingResult result, Model model)
			throws IOException, JsonProcessingException {
		if (result.hasErrors()) {
			return "add-user";
		}
		ResponseEntity<CreditCard> response = restTemplate.exchange(credirCardUrl + "/save", HttpMethod.POST,
				new HttpEntity(creditCard, setRequestHeader(getSession())), CreditCard.class);
		return "home";
	}

	private HttpSession getSession() {
		return httpSessionFactory.getObject();
	}

	private HttpHeaders setRequestHeader(HttpSession session) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + session.getAttribute("authToken"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		return headers;
	}
}
