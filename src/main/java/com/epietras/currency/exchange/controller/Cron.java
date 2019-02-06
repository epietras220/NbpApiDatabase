package com.epietras.currency.exchange.controller;

import com.epietras.currency.exchange.service.ExchangeRatesServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

@Log4j2
@Component
public class Cron {

    private final ExchangeRatesServiceImpl exchangeRatesServiceImpl;

    @Autowired
    public Cron(ExchangeRatesServiceImpl exchangeRatesService) {
        this.exchangeRatesServiceImpl = exchangeRatesService;

    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentExchange() throws IOException {
        log.info("aktualny kurs walut");
        System.out.println(exchangeRatesServiceImpl.returnAllExchange());

        try {
            this.exchangeRatesServiceImpl.returnAllExchange();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
