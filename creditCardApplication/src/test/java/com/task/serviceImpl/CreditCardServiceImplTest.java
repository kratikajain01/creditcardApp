/**
 * 
 */
package com.task.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.task.config.TokenProvider;
import com.task.dao.CreditCardDao;
import com.task.entity.CreditCard;
import com.task.entity.User;
import com.task.exception.ApplicationException;
import com.task.service.UserService;

/**
 * @author kratika.jain
 *
 */
public class CreditCardServiceImplTest {

	@InjectMocks
	CreditCardServiceImpl creditCardService;
	@Mock
	CreditCardDao creditCardDao;
	@Mock
	UserService userService;
	@Mock
	TokenProvider tokenProvider;
	CreditCard creditCard = new CreditCard();

	@BeforeEach
	public void setUp() {
		creditCard.setCreditCardNumber("12435436546");
		MockitoAnnotations.initMocks(this);
		when(tokenProvider.getUsernameFromToken(any())).thenReturn("erfghu7654345tgh");
		when(userService.findOne(any())).thenReturn(new User());
	}

	@Test
	public void saveCard_cardAlreadyExist() {
		when(creditCardDao.checkIfExists(any())).thenReturn(true);
		ApplicationException applicationException = assertThrows(ApplicationException.class,
				() -> creditCardService.saveCard(creditCard, "BEARER erfghu7654345tgh"));
		assertEquals("12435436546 Credit Card details already Exists", applicationException.getMessage());
	}

	@Test
	public void saveCard_cardLengthFails() {
		creditCard.setCreditCardNumber("124354368765438765432546");
		ApplicationException entityExistException = assertThrows(ApplicationException.class,
				() -> creditCardService.saveCard(creditCard, "BEARER erfghu7654345tgh"));
		assertEquals("Length of credit card should be 19 or less", entityExistException.getMessage());
	}

	@Test
	public void saveCard_success() throws Exception {
		when(creditCardDao.save((CreditCard) any())).thenReturn(creditCard);
		when(creditCardDao.checkIfExists(any())).thenReturn(false);
		assertEquals(creditCard, creditCardService.saveCard(creditCard, "BEARER erfghu7654345tgh"));
	}

	@Test
	public void fetchCreditCard_success() {
		List<CreditCard> listOfCreditCards = new ArrayList<>();
		listOfCreditCards.add(creditCard);
		when(creditCardDao.findCCByUser(any())).thenReturn(listOfCreditCards);
		assertEquals(listOfCreditCards, creditCardService.fetchCCByUser(1l));
		assertEquals(1, listOfCreditCards.size());
	}

	@Test
	public void fetchAllCreditCard_success() {
		List<CreditCard> listOfCreditCards = new ArrayList<>();
		listOfCreditCards.add(new CreditCard(1, "12435436546", LocalDate.now(), 2000, "VISA", new User()));
		listOfCreditCards.add(new CreditCard(1, "12435438946", LocalDate.now(), 1000, "MASTER", new User()));
		when(creditCardDao.findAll()).thenReturn(listOfCreditCards);
		assertEquals(listOfCreditCards, creditCardService.fetchAllCreditCard());
		assertEquals(2, listOfCreditCards.size());
	}

}
