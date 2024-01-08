package com.user.transactions.repository;

import com.user.transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//    List<Transaction> findByIdIn(List<Long> accountId);

    List<Transaction> findAllByAccountIdAndUpdatedOn(Long id, Date updatedOn);

    List<Transaction> findByAccountIdIn(List<Long> longs);
}
