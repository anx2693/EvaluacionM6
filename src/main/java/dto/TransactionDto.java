package dto;

import java.math.BigDecimal;

public class TransactionDto {

    private BigDecimal amount;
    private String accountEmail;

    public TransactionDto() {
    }

    public TransactionDto(BigDecimal amount, String accountEmail) {
        this.amount = amount;
        this.accountEmail = accountEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }
}
