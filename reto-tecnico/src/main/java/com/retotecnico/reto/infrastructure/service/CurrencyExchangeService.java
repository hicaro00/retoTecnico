package com.retotecnico.reto.infrastructure.service;

import com.retotecnico.reto.domain.dtos.ExchangeRateDto;
import com.retotecnico.reto.domain.entities.ExchangeRateEntity;
import com.retotecnico.reto.domain.models.ConversionRequest;
import com.retotecnico.reto.domain.models.ConversionResult;
import com.retotecnico.reto.domain.models.UpdateExchangeRateRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyExchangeService {

  Mono<ConversionResult> convertCurrency(ConversionRequest conversionRequest);
  Mono<Void> updateExchangeRate(UpdateExchangeRateRequest updateExchangeRateRequest);
  Flux<ExchangeRateDto> getall();
  public Mono<ExchangeRateDto> guardar(Mono<ExchangeRateDto> exchangeRate);
}
