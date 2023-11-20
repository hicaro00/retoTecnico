package com.retotecnico.reto.application.serviceimpl;


import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

import com.retotecnico.reto.domain.dtos.ExchangeRateDto;
import com.retotecnico.reto.domain.models.ConversionRequest;
import com.retotecnico.reto.domain.models.ConversionResult;
import com.retotecnico.reto.infrastructure.repository.ExchangeRateRepository;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class CurrencyExchangeServiceImplTest {

  @Mock
  private ExchangeRateRepository exchangeRateRepository;


  @InjectMocks
  private CurrencyExchangeServiceImpl currencyExchangeService;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  void convertCurrency() {

    ConversionRequest conversionRequest =
        new ConversionRequest(new BigDecimal("100.00"), "USD", "EUR");
    ExchangeRateDto exchangeRateDto =
        new ExchangeRateDto("kjgb", "USD", "EUR", new BigDecimal("1.20"));


        when(exchangeRateRepository.findByCurrencyFromAndCurrencyTo(any(), any()))
            .thenReturn(Mono.just(exchangeRateDto));


    // Test
    Mono<ConversionResult> resultMono = currencyExchangeService.convertCurrency(conversionRequest);

    StepVerifier.create(resultMono)
        .expectNextMatches(result ->
            result.getAmount().equals(conversionRequest.getAmount()) &&
                result.getConvertedAmount().equals(new BigDecimal("120.00")) &&
                result.getCurrencyFrom().equals("USD") &&
                result.getCurrencyTo().equals("EUR") &&
                result.getExchangeRate().equals(new BigDecimal("1.20"))
        )
        .verifyComplete();
  }
}

