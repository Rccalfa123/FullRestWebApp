package com.cg.RestAPIWithJpa.BankDao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.RestAPIWithJpa.pojo.Transaction;

public interface BankAccountDaoMongo extends MongoRepository<Transaction, Integer> {

}
