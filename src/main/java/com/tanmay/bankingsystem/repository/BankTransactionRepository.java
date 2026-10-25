
package com.tanmay.bankingsystem.repository;

import com.tanmay.bankingsystem.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTransactionRepository
        extends JpaRepository<BankTransaction, Long> {

    List<BankTransaction> findByFromAccountIdOrToAccountId(
            Long fromAccountId,
            Long toAccountId);
}
