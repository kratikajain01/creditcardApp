/**
 * 
 */
package com.task.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.task.config.AttributeEncryptor;
import com.task.entity.CreditCard;
import com.task.entity.User;

/**
 * @author kratika.jain
 *
 */
@DataJpaTest
public class CreditCardDaoTest {

	@Autowired
	CreditCardDao creditCardDao;

	@Test
	public void findCCByUserTest() {
		creditCardDao.save(getCreditCard());
		CreditCard result = creditCardDao.findCCByUser(1L).get(0);
		assertEquals(result.getCreditCardNumber(), "79927398713");
	}

	@Test
	public void checkIfExistsTest() throws Exception{
		AttributeEncryptor encryptCC = new AttributeEncryptor();
		creditCardDao.save(getCreditCard());
		assertTrue(creditCardDao.checkIfExists(encryptCC.convertToDatabaseColumn("79927398713")));
		assertFalse(creditCardDao.checkIfExists(encryptCC.convertToDatabaseColumn("798678927398713")));

	}

	public CreditCard getCreditCard() {
		CreditCard cc = new CreditCard();
		cc.setCreditCardNumber("79927398713");
		cc.setAvailableLimit(100);
		cc.setCardType("VISA");
		cc.setExpiryDate(LocalDate.now());
		cc.setUser(new User(1, "Kratika", null, null, null, null, null));
		return cc;
	}
	
	

}
