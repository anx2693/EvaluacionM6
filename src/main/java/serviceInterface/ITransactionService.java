package serviceInterface;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.TransactionDto;
import dto.TransferDto;
import model.BankAccount;
import model.Transactions;
import repository.TransactionRepository;
import repository.TransactionTypeRepository;
import service.BankAccountService;
import service.TransactionService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ITransactionService implements TransactionService {

    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;

    public ITransactionService(TransactionTypeRepository transactionTypeRepository, TransactionRepository transactionRepository, IBankAccountService bankAccountService) {
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }


    @Override
    @Transactional
    public void deposit(TransactionDto transactionDto) {
        BankAccount bankAccount = bankAccountService.findBankAccountByUserEmail(transactionDto.getAccountEmail()).orElse(null);
        Transactions transaction = new Transactions();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionTypeRepository.findById(2L).orElse(null));
        transaction.setReceiver(null);
        transaction.setSender(bankAccount);
        transaction.setTotal(transactionDto.getAmount());

        if (bankAccount != null) {
            bankAccountService.addAmountToBalance(transaction);
        }

        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void withdraw(TransactionDto transactionDto) {
        BankAccount bankAccount = bankAccountService.findBankAccountByUserEmail(transactionDto.getAccountEmail()).orElse(null);

        if (bankAccount != null && bankAccount.getBalance().compareTo(transactionDto.getAmount()) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }

        Transactions transaction = new Transactions();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionTypeRepository.findById(3L).orElse(null));
        transaction.setReceiver(null);
        transaction.setSender(bankAccount);
        transaction.setTotal(transactionDto.getAmount());

        if (bankAccount != null) {
            bankAccountService.subtractAmountFromBalance(transaction);
        }

        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void transfer(TransferDto transferDto) {
        BankAccount sender = bankAccountService.findBankAccountByUserEmail(transferDto.getSenderAccountEmail()).orElse(null);
        BankAccount receiver = bankAccountService.findBankAccountByUserEmail(transferDto.getReceiverAccountEmail()).orElse(null);
        if (sender == null) {
            throw new IllegalArgumentException("Cuenta de origen no encontrada");
        }
        if (sender.getBalance().compareTo(transferDto.getAmount()) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
        if (receiver == null) {
            throw new IllegalArgumentException("Cuenta de destino no encontrada");
        }
        Transactions transaction = new Transactions();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionType(transactionTypeRepository.findById(1L).orElse(null));
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setTotal(transferDto.getAmount());

        bankAccountService.subtractAmountFromBalance(transaction);

        transactionRepository.save(transaction);
    }

    @Override
    public Collection<Transactions> getTransactionsByUserEmail(String userEmail) {
        BankAccount bankAccount = bankAccountService.findBankAccountByUserEmail(userEmail).orElse(null);
        if (bankAccount != null) {
            return transactionRepository.findTransactionsByAccountId(bankAccount.getId());
        } else {
            return List.of();
        }
    }
}