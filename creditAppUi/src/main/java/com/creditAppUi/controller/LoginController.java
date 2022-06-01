/**
 * 
 */
package com.creditAppUi.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.creditAppUi.entity.Login;
import com.creditAppUi.service.LoginService;

/**
 * @author kratika.jain
 *
 */

@Controller
@SessionAttributes("authToken")
public class LoginController {

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	ObjectFactory<HttpSession> httpSessionFactory;
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("/login")
	public String login(Login user) {
		return "login";
	}	
	
	@GetMapping("/logout")
	public String logout() {
		HttpSession session = httpSessionFactory.getObject();
		session.removeAttribute("authToken");
		return "login";
	}	
	
	@PostMapping("/authenticate")
	public String validateUser(@Valid Login user, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			return "login";
		}
		String token = loginService.authenticate(user);
		HttpSession session = httpSessionFactory.getObject();
		session.setAttribute("authToken", token);
		return "home";
	}	
}
