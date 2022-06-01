/**
 * 
 */
package com.creditAppUi.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.creditAppUi.entity.Login;

/**
 * @author kratika.jain
 *
 */
@Service
public interface LoginService {

	public String authenticate(@Valid Login user) throws Exception;
	

}
