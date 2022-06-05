/**
 * 
 */
package com.task.serviceImpl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.config.AttributeEncryptor;
import com.task.config.TokenProvider;
import com.task.dao.CreditCardDao;
import com.task.entity.CreditCard;
import com.task.entity.User;
import com.task.exception.ApplicationException;
import com.task.service.CreditCardService;
import com.task.service.UserService;

/**
 * @author kratika.jain
 *
 */

@Service
class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private TokenProvider jwtTokenUtil;
	@Autowired
	UserService userService;
	@Autowired
	CreditCardDao creditCardDao;
	

	@Override
	public CreditCard saveCard(CreditCard creditCard,String authorization) throws ApplicationException, NoSuchAlgorithmException, NoSuchPaddingException {
		validateCreditCard(creditCard);
		User user = userService.findOne(jwtTokenUtil.getUsernameFromToken(authorization.replace("Bearer ", "")));
		creditCard.setUser(user);
		return creditCardDao.save(creditCard);
	}

	private void validateCreditCard(CreditCard creditCard)
			throws ApplicationException, NoSuchAlgorithmException, NoSuchPaddingException {
		if (creditCard.getCreditCardNumber().length() > 19) {
			throw new ApplicationException("Length of credit card should be 19 or less");
		}
		AttributeEncryptor encryptCC;
		try {
			encryptCC = new AttributeEncryptor();
		} catch (Exception e) {
			throw new ApplicationException("Unable to process data");
		}

		if (creditCardDao.checkIfExists(encryptCC.convertToDatabaseColumn(creditCard.getCreditCardNumber()))) {
			throw new ApplicationException(creditCard.getCreditCardNumber() + " Credit Card details already Exists");
		}
	}

	@Override
	public List<CreditCard> fetchCCByUser(Long userId) {
		return creditCardDao.findCCByUser(userId);
	}

	@Override
	public List<CreditCard> fetchAllCreditCard() {
		return creditCardDao.findAll();
	}

}
