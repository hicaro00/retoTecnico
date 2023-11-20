package com.retotecnico.reto.infrastructure.repository;

import com.retotecnico.reto.domain.dtos.ExchangeRateDto;
import com.retotecnico.reto.domain.entities.ExchangeRateEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRateRepository extends ReactiveMongoRepository<ExchangeRateEntity,String> {

  Mono<ExchangeRateDto> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
