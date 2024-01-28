package isi.tn.repository;

import isi.tn.entities.AmazonCreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmazonCreditRepo extends JpaRepository<AmazonCreditAccount,Long> {
    Optional<AmazonCreditAccount> findByUserId(String userId);
}
