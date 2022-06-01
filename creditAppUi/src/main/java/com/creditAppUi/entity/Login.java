/**
 * 
 */
package com.creditAppUi.entity;

import javax.validation.constraints.NotBlank;

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
public class Login {

		private long id;

		@NotBlank(message = "Username cannot be empty")
		private String username;

		@NotBlank(message = "Password cannot be empty")
		private String password;

}
