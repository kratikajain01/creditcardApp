/**
 * 
 */
package com.task.creditCardApplication;




import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.task.validators.CreditCardLuhn10Validator;

/**
 * @author kratika.jain
 *
 */

public class CreditCardLuhn10ValidatorTest{

	@Mock
    ConstraintValidatorContext constraintValidatorContext;
	
	@Test
	public void validateCreditCardNumberValidTest() throws Exception{
		CreditCardLuhn10Validator validate = new CreditCardLuhn10Validator();
		assertTrue(validate.isValid("1358954993914435", constraintValidatorContext));
		assertTrue(validate.isValid("79927398713", constraintValidatorContext));
		
	}
	@Test
	public void validateCreditCardNumberNotValidTest() throws Exception{
		CreditCardLuhn10Validator validate = new CreditCardLuhn10Validator();
		assertFalse(validate.isValid("135895499391436", constraintValidatorContext));
		assertFalse(validate.isValid("7992739811", constraintValidatorContext));		
	}
}
