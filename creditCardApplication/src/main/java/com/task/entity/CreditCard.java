/**
 * 
 */
package com.task.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task.config.AttributeEncryptor;
import com.task.validators.CreditCardLuhn10Constraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kratika.jain
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "credit_Card_Number")
	@CreditCardLuhn10Constraint
	@NotBlank(message = "Credit card Number can not be empty")
	@Convert(converter = AttributeEncryptor.class)
	private String creditCardNumber;

	@Column(name = "expiryDate")
	@NotNull
	@FutureOrPresent(message = "Your card is expired")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate expiryDate;

	@Column(name = "availableLimit")
	@NotNull
	private double availableLimit;

	@Column(name = "cardType")
	private String cardType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
