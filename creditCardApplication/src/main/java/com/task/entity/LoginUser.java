/**
 * 
 */
package com.task.entity;

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
public class LoginUser {

	private String username;

	private String password;

}
