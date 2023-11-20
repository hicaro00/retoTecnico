package com.retotecnico.reto.application.serviceimpl;

import com.retotecnico.reto.domain.dtos.ExchangeRateDto;
import com.retotecnico.reto.domain.mapper.Mapper;
import com.retotecnico.reto.domain.models.ConversionRequest;
import com.retotecnico.reto.domain.models.ConversionResult;
import com.retotecnico.reto.domain.models.UpdateExchangeRateRequest;
import com.retotecnico.reto.infrastructure.repository.ExchangeRateRepository;
import com.retotecnico.reto.infrastructure.service.CurrencyExchangeService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
  @Autowired
  ExchangeRateRepository exchangeRateRepository;

  @Override
  public Mono<ConversionResult> convertCurrency(ConversionRequest conversionRequest) {

    String currencyFrom = conversionRequest.getCurrencyFrom();
    String currencyTo = conversionRequest.getCurrencyTo();
    return exchangeRateRepository.findByCurrencyFromAndCurrencyTo(currencyFrom, currencyTo)
        .map(exchangeRate -> {
          BigDecimal convertedAmount =
              conversionRequest.getAmount().multiply(exchangeRate.getRate());
          return new ConversionResult(conversionRequest.getAmount(),
              convertedAmount,
              conversionRequest.getCurrencyFrom(),
              conversionRequest.getCurrencyTo(),
              exchangeRate.getRate());
        });
  }

  @Override

  public Mono<Void> updateExchangeRate(UpdateExchangeRateRequest updateExchangeRateRequest) {
    String currencyFrom = updateExchangeRateRequest.getCurrencyFrom();
    String currencyTo = updateExchangeRateRequest.getCurrencyTo();

    return exchangeRateRepository.findByCurrencyFromAndCurrencyTo(currencyFrom, currencyTo)
        .flatMap(existingExchangeRate -> {
          existingExchangeRate.setRate(updateExchangeRateRequest.getNewRate());//para actualizar
          return exchangeRateRepository.save(Mapper.dtoToEntity(existingExchangeRate));
        })
        .then(Mono.just("Tipo de cambio actualizado con éxito")).then();

  }

  @Override
  public Flux<ExchangeRateDto> getall() {
    return exchangeRateRepository.findAll().map(Mapper::entityToDto);
  }
  @Override
  public Mono<ExchangeRateDto> guardar(Mono<ExchangeRateDto> exchangeRate){

    return exchangeRate.map(Mapper::dtoToEntity).flatMap(exchangeRateRepository::insert).map(Mapper::entityToDto);
  }


}
