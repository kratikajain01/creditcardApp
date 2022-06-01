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
import com.task.dao.CreditCardDao;
import com.task.entity.CreditCard;
import com.task.exception.ApplicationException;
import com.task.service.CreditCardService;

/**
 * @author kratika.jain
 *
 */

@Service
class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	CreditCardDao creditCardDao;
	

	@Override
	public CreditCard saveCard(CreditCard creditCard) throws ApplicationException, NoSuchAlgorithmException, NoSuchPaddingException {
		validateCreditCard(creditCard);
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
