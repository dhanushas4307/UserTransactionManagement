package com.user.transactions.service;

import com.user.transactions.dto.RateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CurrencyService {

    private static Map<String, BigDecimal> currencyDtoList = new LinkedHashMap<>();

    public static Map<String, BigDecimal> getCurrencyDtoList(){
        return currencyDtoList;
    }

    static {
        generateConstantsFromApi();
    }
    public static void generateConstantsFromApi() {
        RestTemplate restConfig = new RestTemplate();
        String response = restConfig.getForObject("https://api.fxratesapi.com/latest", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        RateDTO rateDTO;
        try {
            rateDTO = objectMapper.readValue(response, RateDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Map.Entry<String, BigDecimal> currency : rateDTO.getCurrencyDTOList().entrySet()){
            getCurrencyDtoList().putIfAbsent(currency.getKey(), currency.getValue());
        }

    }
    public boolean isValidCurrencyType(String currency) {
       return getCurrencyDtoList().containsKey(currency);
    }

}
