package com.epietras.currency.exchange.service;

import com.epietras.currency.exchange.dto.ArrayOfExchangeRatesTableDto;
import com.epietras.currency.exchange.dto.RateDto;
import com.epietras.currency.exchange.mapper.RateMapper;
import com.epietras.currency.exchange.model.Rate;
import com.epietras.currency.exchange.repository.RatesRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ExchangeRatesServiceImpl {

    // private RateMapper rateMapper = RateMapper.INSTANCE;       // todo skorzystaj z tego w poniższych metodach



    private RatesRepository ratesRepository;

    public ExchangeRatesServiceImpl(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    private ArrayOfExchangeRatesTableDto arrayOfExchangeRatesTableDto;


    public ArrayOfExchangeRatesTableDto returnAllExchange() throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));

        HttpEntity<String> entity = new HttpEntity<>("headers", headers);

        String resourceUrl = "http://api.nbp.pl/api/exchangerates/tables/C/today/";
        ResponseEntity<String> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);

        String responseBody = responseEntity.getBody();
        log.info(responseBody);

        XmlMapper xmlMapper = new XmlMapper();

        ArrayOfExchangeRatesTableDto arrayOfExchangeRatesTableDto = xmlMapper.readValue(responseBody, ArrayOfExchangeRatesTableDto.class);

        return arrayOfExchangeRatesTableDto;
    }

    public List<String> returnAllCodes() {

        List<String> list = arrayOfExchangeRatesTableDto.getExchangeRatesTableDto().getRates()
                .stream()
                .map(RateDto::getCode)
                .collect(Collectors.toList());
        return list;
    }

    public void saveRateDtoToDatabase() {
        List<RateDto> rateDtoList = arrayOfExchangeRatesTableDto.getExchangeRatesTableDto().getRates();

        for (RateDto rateDto : rateDtoList) {
            Rate rate = RateMapper.INSTANCE.exchangeDtoToEntity(rateDto);
            this.ratesRepository.save(rate);
        }
    }

    public void updateDatabase(RateDto rateDto) {
        Rate rate = RateMapper.INSTANCE.exchangeDtoToEntity(rateDto);

        Rate rate2 = this.ratesRepository.findById(rate.getId())
                .orElseThrow(() -> new RuntimeException());

        rate2.setId(rate.getId());
        rate2.setAsk(rate.getAsk());
        rate2.setBid(rate.getBid());
        rate2.setCode(rate.getCode());
        rate2.setCurrency(rate.getCurrency());

        this.ratesRepository.save(rate2);
    }

    public void insertToDatabase(RateDto rateDto) {
        Rate rate = RateMapper.INSTANCE.exchangeDtoToEntity(rateDto);
        this.ratesRepository.save(rate);
    }

}