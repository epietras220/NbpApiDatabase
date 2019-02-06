package com.epietras.currency.exchange.controller;

import com.epietras.currency.exchange.dto.ArrayOfExchangeRatesTableDto;
import com.epietras.currency.exchange.dto.RateDto;
import com.epietras.currency.exchange.service.ExchangeRatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExchangeRestController {


    private ExchangeRatesServiceImpl exchangeRatesService;

    public ExchangeRestController(ExchangeRatesServiceImpl exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/exchange")
    public ArrayOfExchangeRatesTableDto returnAllExchange() throws IOException {

        return exchangeRatesService.returnAllExchange();
    }

    @PostMapping("/toDatabase")
    public void insertToDatabase(RateDto rateDto){
        exchangeRatesService.insertToDatabase(rateDto);
    }

    @PutMapping("/toDatabase")
    public void updateDatabase(RateDto rateDto){
        exchangeRatesService.updateDatabase(rateDto);
    }
}
