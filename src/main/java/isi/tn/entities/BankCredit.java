package isi.tn.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "bankCredit")
public class BankCredit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_seq")
    @SequenceGenerator(name = "operation_seq", sequenceName = "operation_seq", allocationSize = 1, initialValue = 1)
    private Long bankWalletId;
    private double balance;
    private String userId;
    private LocalDateTime lastActivityDate = LocalDateTime.now();

    public BankCredit() {

    }
    public BankCredit(Long bankWalletId, double balance, String userId) {
        this.bankWalletId = bankWalletId;
        this.balance = balance;
        this.userId = userId;
    }


    public Long getBankWalletId() {
        return bankWalletId;
    }

    public void setBankWalletId(Long bankWalletId) {
        this.bankWalletId = bankWalletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(LocalDateTime lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }
}
