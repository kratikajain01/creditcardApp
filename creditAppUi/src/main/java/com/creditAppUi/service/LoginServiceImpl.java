/**
 * 
 */
package com.creditAppUi.service;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.creditAppUi.entity.AuthToken;
import com.creditAppUi.entity.Login;

/**
 * @author kratika.jain
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public String authenticate(@Valid Login loginUser) throws Exception {
		String credirCardLoginUrl = "http://localhost:8081/authenticate";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AuthToken> response = restTemplate.exchange(credirCardLoginUrl, HttpMethod.POST,
				new HttpEntity(loginUser, setRequestHeader()), AuthToken.class);
		return response.getBody().getToken();
	}

	private HttpHeaders setRequestHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		return headers;
	}

}
