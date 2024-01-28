package isi.tn.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class AmazonCreditAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userId;
	private Double creditBalance;
	private LocalDateTime lastActivityDate = LocalDateTime.now();


	public AmazonCreditAccount(Long id, String userId, Double creditBalance) {
		this.id = id;
		this.userId = userId;
		this.creditBalance = creditBalance;
	}

	public AmazonCreditAccount() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(Double creditBalance) {
		this.creditBalance = creditBalance;
	}

	public LocalDateTime getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(LocalDateTime lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}


	@Override
	public String toString() {
		return "AmazonCreditAccount{" +
				"userId='" + userId + '\'' +
				", creditBalance=" + creditBalance +
				", lastActivityDate=" + lastActivityDate + '\'' +
				'}';
	}
}