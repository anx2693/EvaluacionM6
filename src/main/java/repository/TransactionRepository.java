package repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Transactions;

import java.util.Collection;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    @Query("SELECT t FROM Transactions t WHERE t.sender.id = :accountId OR t.receiver.id = :accountId ORDER BY t.transactionDate DESC")
    Collection<Transactions> findTransactionsByAccountId(@Param("accountId") Integer accountId);
}