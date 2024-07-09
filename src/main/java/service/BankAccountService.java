
package service;

import java.util.Optional;

import model.BankAccount;
import model.Transactions;

public interface BankAccountService {
    Optional<BankAccount> findBankAccountByUserEmail(String userEmail);
    void addAmountToBalance(Transactions transaction);
    void subtractAmountFromBalance(Transactions transaction);
}
