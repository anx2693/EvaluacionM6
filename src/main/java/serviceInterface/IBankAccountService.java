package serviceInterface;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import model.BankAccount;
import model.Transactions;
import repository.BankAccountRepository;
import service.BankAccountService;

@Service
public class IBankAccountService implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public IBankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Optional<BankAccount> findBankAccountByUserEmail(String userEmail) {
        return bankAccountRepository.findByOwnerEmail(userEmail);
    }

    @Override
    @Transactional
    public void addAmountToBalance(Transactions transaction) {
        BankAccount sender = transaction.getSender();
        if (sender != null) {
            sender.setBalance(sender.getBalance().add(transaction.getTotal()));
            bankAccountRepository.save(sender);
        }
    }

    @Override
    @Transactional
    public void subtractAmountFromBalance(Transactions transaction) {
        BankAccount sender = transaction.getSender();
        if (sender != null) {
            sender.setBalance(sender.getBalance().subtract(transaction.getTotal()));
            bankAccountRepository.save(sender);
        }

        if(transaction.getTransactionType().getId() == 1) {
            BankAccount receiver = transaction.getReceiver();
            if (receiver != null) {
                receiver.setBalance(receiver.getBalance().add(transaction.getTotal()));
                bankAccountRepository.save(receiver);
            }
        }
    }

}