package isi.tn.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "conversionOperation")
public class ConversionOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer operationId;
    private String currencyFrom;
    private String currencyTo;
    private double initialAmount;
    private Double conversionRate;
    private double convertedAmount;

    // Constructors

    public ConversionOperation() {
        // Default constructor
    }

    public ConversionOperation(Integer operationId, String currencyFrom, String currencyTo, double initialAmount,
                               Double conversionRate, double convertedAmount) {
        this.operationId = operationId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.initialAmount = initialAmount;
        this.conversionRate = conversionRate;
        this.convertedAmount = convertedAmount;
    }

    // Getters and Setters

    public Integer getIdOperations() {
        return operationId;
    }

    public void setIdOperations(Integer idOperations) {
        this.operationId = idOperations;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }


    @Override
    public String toString() {
        return "ConversionOperation{" +
                "operationId=" + operationId +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", initialAmount=" + initialAmount +
                ", convertedAmount=" + convertedAmount +
                ", conversionRate=" + conversionRate +
                '}';
    }
}