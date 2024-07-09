package dto;
import java.math.BigDecimal;

public class TransferDto {
    private BigDecimal amount;
    private String senderAccountEmail;
    private String receiverAccountEmail;

    public TransferDto() {
    }

    public TransferDto(BigDecimal amount, String senderAccountEmail, String receiverAccountEmail) {
        this.amount = amount;
        this.senderAccountEmail = senderAccountEmail;
        this.receiverAccountEmail = receiverAccountEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSenderAccountEmail() {
        return senderAccountEmail;
    }

    public void setSenderAccountEmail(String senderAccountEmail) {
        this.senderAccountEmail = senderAccountEmail;
    }

    public String getReceiverAccountEmail() {
        return receiverAccountEmail;
    }

    public void setReceiverAccountEmail(String receiverAccountEmail) {
        this.receiverAccountEmail = receiverAccountEmail;
    }
}