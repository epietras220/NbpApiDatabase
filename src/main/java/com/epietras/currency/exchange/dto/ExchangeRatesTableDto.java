package com.epietras.currency.exchange.dto;


import com.epietras.currency.exchange.model.Rate;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "ExchangeRatesTable")
public class ExchangeRatesTableDto {

    @JacksonXmlProperty(localName = "Table")
    private char table;

    @JacksonXmlProperty(localName = "No")
    private String no;

    @JacksonXmlProperty(localName = "TradingDate")
    private String tradingDate;

    @JacksonXmlProperty(localName = "EffectiveDate")
    private String effectiveDate;

    @JacksonXmlElementWrapper(localName = "Rates")
    @JacksonXmlProperty(localName = "Rate")
    private List<RateDto> rates = new ArrayList<>();
}
