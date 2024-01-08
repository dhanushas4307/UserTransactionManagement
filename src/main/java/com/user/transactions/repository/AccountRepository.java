package com.user.transactions.repository;

import com.user.transactions.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
//    Account findByAccount_Id(Long accountId);

    Account findByAccountId(Long id);
}
