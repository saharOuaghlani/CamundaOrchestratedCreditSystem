package isi.tn.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import isi.tn.entities.AmazonCreditAccount;
import isi.tn.repository.AmazonCreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/credit")
public class AmazonCreditController {

	@Autowired
	AmazonCreditRepo crepo;

	@PostMapping("/create")
	public AmazonCreditAccount createCrew(@Valid @RequestBody AmazonCreditAccount account) {
		return crepo.save(account);
	}

	@GetMapping("/verify/{userId}/{amount}")
	public ResponseEntity<Map<String, Integer>> verifyCredit(@PathVariable String userId, @PathVariable Double amount) {
		try {
			AmazonCreditAccount creditWallet = crepo.findByUserId(userId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

			Double balance = creditWallet.getCreditBalance();
			int enoughCredit = (balance < amount) ? 0 : 1;

			Map<String, Integer> response = new HashMap<>();
			response.put("enoughCredit", enoughCredit);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("enoughCredit", -1));
		}
	}

	@PutMapping("/deduct/{userId}/{amount}")
	public ResponseEntity<String> deductCredit(@PathVariable String userId, @PathVariable Double amount) {
		return crepo.findByUserId(userId)
				.map(creditWallet -> {
					if (creditWallet != null) {
						Double actualCredit = creditWallet.getCreditBalance();
						creditWallet.setCreditBalance(actualCredit - amount);
						creditWallet.setLastActivityDate(LocalDateTime.now());
						crepo.save(creditWallet);
						return ResponseEntity.ok("Credit deducted successfully");
				} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
				})
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
	}
}
