package com.epietras.currency.exchange.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.xml.bind.annotation.*;


@Data
public class RateDto {

    Integer id;

    @JacksonXmlProperty(localName = "Currency")
    private String currency;

    @JacksonXmlProperty(localName = "Code")
    private String code;

    @JacksonXmlProperty(localName = "Bid")
    private Float bid;

    @JacksonXmlProperty(localName = "Ask")
    private Float ask;
}
