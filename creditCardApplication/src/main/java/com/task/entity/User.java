/**
 * 
 */
package com.task.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Table(name = "UserEntity")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "Username")
	@NotBlank(message = "Username cannot be empty")
	private String username;

	@Column(name = "Password")
	@NotBlank(message = "Password cannot be empty")
	private String password;

	@Column(name = "Email")
	@NotBlank(message = "Email cannot be empty")
	private String email;

	@Column(name = "Phone")
	@NotBlank(message = "Phone number cannot be empty")
	private String phone;

	@Column(name = "Name")
	@NotBlank(message = "Name cannot be empty")
	private String name;

	/**
	 * MappedBy indicates that this column is owned by different entity
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<CreditCard> creditCards;

}
