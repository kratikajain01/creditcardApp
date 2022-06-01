/**
 * 
 */
package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.config.TokenProvider;
import com.task.entity.AuthToken;
import com.task.entity.LoginUser;

/**
 * @author kratika.jain
 * 
 * This Rest Api is responsible for authentication of incoming requests
 * from client which has username and password 
 * and return a JWT token to client
 * 
 */
@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

	private TokenProvider jwtTokenUtil;

	public AuthenticationController(AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;

	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
	}
}
