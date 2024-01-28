package isi.tn.service;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import isi.tn.entities.ConversionRate;
import isi.tn.repository.conversionRateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Service
public class ExchangeRateService {

    @Autowired
    private conversionRateRepo conversionRateRepository;

    @Value("${api.key}")
    private String apiKey;

    @Scheduled(cron = "0 0 0 * * *")  // Scheduled task to update exchange rates every day at midnight
    @Async
    public void fetchAndSaveExchangeRates() {

        String apiUrl = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/";
        String csvFilePath = "src/main/resources/data.csv";
        String url = "";
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            boolean firstLine = true;

            while ((nextLine = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] fields = nextLine[0].split(";");// Utiliser le point-virgule comme délimiteur pour diviser la ligne
                String baseCurrency = fields[0];// Récupérer le contenu de la première partie de chaque ligne
                url = apiUrl + baseCurrency;

                RestTemplate restTemplate = new RestTemplate();
                Map<String, Object> response = restTemplate.getForObject(url, Map.class);

                if (response != null && "success".equals(response.get("result"))) {
                    updateDataset(response);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void updateDataset(Map<String, Object> apiResponse) {
        String baseCurrency = (String) apiResponse.get("base_code");
        String lastUpdateUtc = (String) apiResponse.get("time_last_update_utc");
        String nextUpdateUtc = (String) apiResponse.get("time_next_update_utc");

        Date lastUpdateDate = parseUtcDate(lastUpdateUtc);
        Date nextUpdateDate = parseUtcDate(nextUpdateUtc);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(apiResponse);
            JsonNode jsonNode = objectMapper.readTree(jsonString);// Parse JSON response
            JsonNode conversionRatesNode = jsonNode.get("conversion_rates");// Extract conversion_rates object

            conversionRatesNode.fields().forEachRemaining(entry -> {
                String currency = entry.getKey();
                double rate = entry.getValue().asDouble();

                ConversionRate conversionRate = conversionRateRepository.findByCurrencyFromAndCurrencyTo(baseCurrency, currency)
                        .orElse(new ConversionRate());
                conversionRate.setCurrencyFrom(baseCurrency);
                conversionRate.setCurrencyTo(currency);
                conversionRate.setRate(rate);
                conversionRate.setValidityStartDate(lastUpdateDate);
                conversionRate.setValidityEndDate(nextUpdateDate);
                conversionRateRepository.save(conversionRate);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Date parseUtcDate(String utcDate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(utcDate, dateFormat);
            return Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

