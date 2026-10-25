
package com.tanmay.bankingsystem.repository;

import com.tanmay.bankingsystem.entity.FailedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedTransactionRepository
        extends JpaRepository<FailedTransaction, Long> {
}
