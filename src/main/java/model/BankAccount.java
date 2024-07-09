package model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_accounts")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User owner;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "sender")
    @JsonManagedReference
    private Collection<Transactions> transactions;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, BigDecimal balance, User owner, Currency currency) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Collection<Transactions> getTransactions() {
        // Sort by date
        return transactions.stream().sorted(Comparator.comparing(Transactions::getTransactionDate).reversed()).toList();
    }

    public void setTransactions(Collection<Transactions> transactions) {
        this.transactions = transactions;
    }
}
