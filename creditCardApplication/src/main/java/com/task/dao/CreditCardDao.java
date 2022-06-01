/**
 * 
 */
package com.task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.entity.CreditCard;

/**
 * @author kratika.jain
 * 
 *         Saves Credit card details to DB
 * 
 */
@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Long> {

	@Query(value = "Select * from Credit_Card where USER_ID=?1", nativeQuery = true)
	List<CreditCard> findCCByUser(Long userId);

	@Query(value = "Select COUNT(CREDIT_CARD_NUMBER)>0 from Credit_Card where CREDIT_CARD_NUMBER=?1", nativeQuery = true)
	boolean checkIfExists(String creditCardNumber);
}
