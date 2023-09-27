package com.example.solvaProject.checkCurrency;


import com.example.solvaProject.model.CurrencyEntity;
import com.example.solvaProject.repository.CurrencyRep;
import com.example.solvaProject.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@EnableScheduling
public class ExchangeCurrency {

    private final RestTemplateService restTemplateService;
    private final CurrencyRep currencyRep;
    @Value("${api.uri}")
    private String uri;

    @Value("${api.key}")
    private String key;

    @Scheduled(cron = "* * 12 * * *")
//    @Scheduled(cron = "* * * * * *")
    public void check(){
        changeCurrency("USD", "KZT");
        changeCurrency("USD", "JPY");
        changeCurrency("KZT", "USD");
        changeCurrency("KZT", "JPY");
        changeCurrency("JPY", "USD");
        changeCurrency("USD", "KZT");
    }

        public void changeCurrency(String fromCurrency, String toCurrency){
        try {
            String url = uri + fromCurrency + "&to_currency=" + toCurrency + "&apikey=" + key;
            String currencyRate = restTemplateService.send(url, null, String.class);
            JSONObject obj = new JSONObject(currencyRate);

            String currencyFrom = obj.getJSONObject("Realtime Currency Exchange Rate").getString("1. From_Currency Code");
            String currencyTo = obj.getJSONObject("Realtime Currency Exchange Rate").getString("3. To_Currency Code");
            String currencyExchange = obj.getJSONObject("Realtime Currency Exchange Rate").getString("5. Exchange Rate");
            CurrencyEntity finalCurrencyRateEntity = CurrencyEntity.builder()
                    .fromCurrency(currencyFrom)
                    .toCurrency(currencyTo)
                    .exchangeRate(currencyExchange)
                    .build();
            if (finalCurrencyRateEntity != null){
                currencyRep.save(finalCurrencyRateEntity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
