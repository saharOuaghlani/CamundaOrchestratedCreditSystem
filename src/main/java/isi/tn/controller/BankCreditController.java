package isi.tn.controller;

import isi.tn.entities.BankCredit;
import isi.tn.repository.BankCreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/bankwallet")
public class BankCreditController {

	@Autowired
	BankCreditRepo crepo;

	@PostMapping("/create")
	public BankCredit createCrew(@Valid @RequestBody BankCredit account) {
		return crepo.save(account);
	}

	@GetMapping("/verify/{userId}/{amount}")
	public ResponseEntity<Map<String, Integer>> verifyCredit(@PathVariable String userId, @PathVariable Double amount) {
		try {
			BankCredit bankWallet = crepo.findByUserId(userId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

			Double balance = bankWallet.getBalance();
			int enoughCredit = (balance < amount) ? 0 : 1;

			Map<String, Integer> response = new HashMap<>();
			response.put("enoughMoney", enoughCredit);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("enoughMoney", -1));
		}
	}

	@PutMapping("/deduct/{userId}/{amount}")
	public ResponseEntity<String> deductCredit(@PathVariable String userId, @PathVariable Double amount) {
		return crepo.findByUserId(userId)
				.map(creditWallet -> {
					if (creditWallet != null) {
						Double actualCredit = creditWallet.getBalance();
						creditWallet.setBalance(actualCredit - amount);
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
