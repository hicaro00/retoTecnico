package com.retotecnico.reto.domain.mapper;


import com.retotecnico.reto.domain.dtos.ExchangeRateDto;
import com.retotecnico.reto.domain.entities.ExchangeRateEntity;
import org.springframework.beans.BeanUtils;

public class Mapper {

    private Mapper(){
    }
        public static ExchangeRateDto entityToDto(ExchangeRateEntity exchangeRateEntity) {
           ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
            BeanUtils.copyProperties(exchangeRateEntity, exchangeRateDto);
            return exchangeRateDto;
        }

        public static ExchangeRateEntity dtoToEntity(ExchangeRateDto exchangeRateDto) {
            ExchangeRateEntity exchangeRateEntity = new ExchangeRateEntity();
            BeanUtils.copyProperties(exchangeRateDto, exchangeRateEntity);
            return exchangeRateEntity;
        }



}

