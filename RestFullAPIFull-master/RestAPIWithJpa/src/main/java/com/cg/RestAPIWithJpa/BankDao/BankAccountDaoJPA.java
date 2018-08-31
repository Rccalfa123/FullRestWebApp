package com.cg.RestAPIWithJpa.BankDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.RestAPIWithJpa.pojo.BankAccount;

@Repository
public interface BankAccountDaoJPA extends JpaRepository<BankAccount, Integer> {

	
	
}
