package isi.tn.repository;

import isi.tn.entities.ConversionOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionOperationRepo  extends JpaRepository<ConversionOperation,Integer> {
}
