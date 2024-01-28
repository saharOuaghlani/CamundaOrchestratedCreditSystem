package isi.tn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "conversionRate")
public class ConversionRate implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer rateId;
	private String currencyFrom;
	private String currencyTo;
	private Double Rate;
	private Date validityStartDate;
	private Date validityEndDate;

	public ConversionRate(Integer rateId, String currencyFrom, String currencyTo, Double rate, Date validityStartDate, Date validityEndDate) {
		this.rateId = rateId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.Rate = rate;
		this.validityStartDate = validityStartDate;
		this.validityEndDate = validityEndDate;
	}

	public ConversionRate() {
		super();
	}


	public Integer getId() {
		return rateId;
	}

	public void setId(Integer id) {
		this.rateId = id;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return this.currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public Double getRate() {
		return Rate;
	}

	public void setRate(Double rate) {
		this.Rate = rate;
	}

	public Date getValidityStartDate() {
		return this.validityStartDate;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	public Date getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	@Override
	public String toString() {
		return "ConversionRate{" +
				"id=" + rateId +
				", CurrencyFrom='" + currencyFrom + '\'' +
				", CurrencyTo='" + currencyTo + '\'' +
				", Rate=" + Rate +
				", validityStartDate=" + validityStartDate +
				", validityEndDate=" + validityEndDate +
				'}';
	}
}