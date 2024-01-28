package isi.tn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import isi.tn.entities.ConversionRate;
import isi.tn.repository.conversionRateRepo;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/rate")
public class conversionRateController {

	@Autowired
	conversionRateRepo crepo;

	@GetMapping("/getALLConversionRate")
	public List<ConversionRate> getAllconversionRate() {
		List<ConversionRate> repo = crepo.findAll();
		for (ConversionRate rate : repo) {
			System.out.println("sysout:   "+rate);
		}
        return repo;
	}

	@GetMapping("/{currencyfrom}/{currencyto}")
	public Map<String, Double> getConversionRate(@PathVariable String currencyfrom, @PathVariable String currencyto) {
		Double rate = crepo.findByCurrencyFromAndCurrencyTo(currencyfrom, currencyto)
				.map(ConversionRate::getRate)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"No conversion rate found for the specified currencies!"));

		Map<String, Double> response = new HashMap<>();
		response.put("rate", rate);

		return response;
	}
}
