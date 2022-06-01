/**
 * 
 */
package com.creditAppUi.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kratika.jain
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Token;
}
