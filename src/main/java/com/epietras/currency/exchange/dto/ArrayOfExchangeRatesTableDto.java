package com.epietras.currency.exchange.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "ArrayOfExchangeRatesTable")
@Data
public class ArrayOfExchangeRatesTableDto {

    @JacksonXmlProperty(localName = "ExchangeRatesTable")
    private ExchangeRatesTableDto exchangeRatesTableDto;

}
