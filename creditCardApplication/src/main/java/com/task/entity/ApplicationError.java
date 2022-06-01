/**
 * 
 */
package com.task.entity;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kratika.jain
 * 
 *         This class holds details of error or exceptions
 * 
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationError {

	private int code;
	private String message;
	private HttpStatus status;

}
