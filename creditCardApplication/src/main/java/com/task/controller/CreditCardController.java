/**
 * 
 */
package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.entity.CreditCard;
import com.task.service.CreditCardService;

/**
 * @author kratika.jain
 *
 * This Rest Api performs get and save credit cards
 * 
 */

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

	@Autowired
	CreditCardService creditCardService;
	
	/**
	 * 
	 * @param userId
	 * @return ResponseEntity<List<CreditCard>>
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/get")
	public ResponseEntity<List<CreditCard>> getCreditCardDetails(@RequestParam Long userId) {
		return new ResponseEntity<>(creditCardService.fetchCCByUser(userId), HttpStatus.OK);
	}

	/**
	 * 
	 * @param creditCard
	 * @return ResponseEntity<CreditCard>
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public ResponseEntity<CreditCard> saveCreditCard(@RequestBody CreditCard creditCard,@RequestHeader(value = "Authorization") String authorization) throws Exception {
		return new ResponseEntity<>(creditCardService.saveCard(creditCard,authorization), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @return ResponseEntity<List<CreditCard>>
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<CreditCard>> getAllCreditCardDetails() {
		return new ResponseEntity<>(creditCardService.fetchAllCreditCard(), HttpStatus.OK);
	}

}
