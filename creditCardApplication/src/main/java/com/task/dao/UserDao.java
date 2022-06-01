/**
 * 
 */
package com.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.entity.User;

/**
 * @author kratika.jain
 *
 *         Performs User related DB operation findByYserName : return user
 *         details on the basis of username
 * 
 */

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	

}
