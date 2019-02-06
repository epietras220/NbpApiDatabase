package com.epietras.currency.exchange.mapper;


import com.epietras.currency.exchange.dto.RateDto;
import com.epietras.currency.exchange.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RateMapper {
    RateMapper INSTANCE = Mappers.getMapper(RateMapper.class);
    Rate exchangeDtoToEntity(RateDto rateDto);



}
