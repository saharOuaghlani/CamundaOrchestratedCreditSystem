package isi.tn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isi.tn.entities.ConversionRate;
import java.util.Date;
import java.util.Optional;

@Repository

public interface conversionRateRepo extends JpaRepository<ConversionRate,Long> {
        Optional<ConversionRate> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
        Optional<ConversionRate> findByCurrencyFromAndCurrencyToAndValidityStartDate(String currencyFrom, String currencyTo, Date validityStartDate);
}
