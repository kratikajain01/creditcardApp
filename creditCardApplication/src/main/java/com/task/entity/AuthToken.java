/**
 * 
 */
package com.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author kratika.jain
 * 
 *         Holds token and returned to the client
 * 
 */

@Data
@AllArgsConstructor
public class AuthToken {

	private String Token;
}
