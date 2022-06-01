/**
 * 
 */
package com.task.serviceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.dao.UserDao;
import com.task.entity.User;
import com.task.service.UserService;

/**
 * @author kratika.jain
 *
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
	
	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}
}
