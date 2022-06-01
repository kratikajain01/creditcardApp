/**
 * 
 */
package com.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.entity.CreditCard;
import com.task.exception.ApplicationException;

/**
 * 
 * @author kratika.jain
 *
 */

@Service
public interface CreditCardService {

	CreditCard saveCard(CreditCard creditCard) throws ApplicationException, Exception;

	List<CreditCard> fetchCCByUser(Long userId);

	List<CreditCard> fetchAllCreditCard();
}
