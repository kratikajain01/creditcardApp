/**
 * 
 */
package com.task.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.task.entity.User;

/**
 * @author kratika.jain
 *
 */

@Service
public interface UserService {

	Collection<? extends GrantedAuthority> getAuthorities();

	User findOne(String username);

}
