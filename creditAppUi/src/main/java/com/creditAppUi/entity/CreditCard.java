/**
 * 
 */
package com.creditAppUi.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kratika.jain
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

	private long id;
	
	private String creditCardNumber;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate expiryDate;

	private double availableLimit;

	private User user;

}
