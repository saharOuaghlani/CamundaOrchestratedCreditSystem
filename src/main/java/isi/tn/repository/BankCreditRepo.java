package isi.tn.repository;

import isi.tn.entities.BankCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankCreditRepo extends JpaRepository<BankCredit,Long> {
    Optional<BankCredit> findByUserId(String userId);
}
