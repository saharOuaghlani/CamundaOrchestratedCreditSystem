package isi.tn.controller;

import isi.tn.entities.ConversionOperation;
import isi.tn.repository.ConversionOperationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/conversion")
public class ConversionOperationController {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ConversionOperationRepo conversionOperationrepo;
    @PostMapping("/convertAmount/{currencyFrom}/{currencyTo}/{conversionRate}/{initialAmount}")
    public ConversionOperation convertCurrency(@PathVariable String currencyFrom, @PathVariable String currencyTo, @PathVariable Double conversionRate, @PathVariable double initialAmount) {

        double convertedAmount = initialAmount * conversionRate;// Calculate converted amount

        ConversionOperation conversionOperation = new ConversionOperation();
        conversionOperation.setCurrencyFrom(currencyFrom);
        conversionOperation.setCurrencyTo(currencyTo);
        conversionOperation.setInitialAmount(initialAmount);
        conversionOperation.setConversionRate(conversionRate);
        conversionOperation.setConvertedAmount(convertedAmount);
        conversionOperationrepo.save(conversionOperation);
        return conversionOperation;
    }
}