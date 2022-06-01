package com.task.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kratika.jain Luhn10 Algo Logic
 * 
 *         1.Starting from right ,double second other digit, if greater than 9
 *         mod 10 and +1 to the remainder 2.Add up all digits 3.If the number is
 *         mutiple of 10, it is valid
 * 
 * 
 */
public class CreditCardLuhn10Validator implements ConstraintValidator<CreditCardLuhn10Constraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			if (!value.isBlank()) {
				return validateCreditCardNumber(Long.parseLong(value));
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Validates Credit Number using Luhn Algorithm
	 * 
	 * @param value
	 * @return
	 */
	private boolean validateCreditCardNumber(Long value) {
		int sum = 0;
		int digitFromRight;
		long temp = value;
		int count = 0;
		while (temp != 0) {
			digitFromRight = (int) (temp % 10);
			temp = temp / 10;

			if (count % 2 != 0) {
				digitFromRight = digitFromRight * 2;
				if (digitFromRight > 9) {
					digitFromRight = digitFromRight % 10 + 1;
				}
			}
			sum = sum + digitFromRight;
			count++;
		}
		if (sum % 10 == 0) {
			return true;
		}

		return false;
	}

}
