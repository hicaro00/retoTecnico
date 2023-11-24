package com.retotecnico.reto.infrastructure.controller;

import com.retotecnico.reto.domain.dtos.ExchangeRateDto;
import com.retotecnico.reto.domain.models.ConversionRequest;
import com.retotecnico.reto.domain.models.ConversionResult;
import com.retotecnico.reto.domain.models.UpdateExchangeRateRequest;
import com.retotecnico.reto.infrastructure.service.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/exchange")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CurrencyExchangeController {

  @Autowired
  CurrencyExchangeService currencyExchangeService;
  private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);


  @PostMapping("/convert")

  public Mono<ResponseEntity<ConversionResult>> convertCurrency(
      @RequestBody Mono<ConversionRequest> conversionRequest) {
    return conversionRequest.flatMap(request ->
        currencyExchangeService.convertCurrency(request)
            .map(ResponseEntity::ok)
            .onErrorResume(ex -> {
              logger.error("Error al convertir divisa", request, ex);
              return Mono.error(ex);

            }));

  }

  @PostMapping("/update-rate")
  public Mono<ResponseEntity<String>> updateExchangeRate(@RequestBody Mono<UpdateExchangeRateRequest> updateExchangeRateRequest) {
    return updateExchangeRateRequest.flatMap(request ->
        currencyExchangeService.updateExchangeRate(request)
            .map(ResponseEntity::ok)
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error al actualizar en la BD")))
    );
  }

  @GetMapping("/traer")
  @ResponseBody
  Flux<ExchangeRateDto> traer (){

   return currencyExchangeService.getall();

  }
 @PostMapping("post")
 Mono<ExchangeRateDto> guardar(@RequestBody Mono<ExchangeRateDto> exchangeRateDto  ) {
    return currencyExchangeService.guardar(exchangeRateDto) ;
  }

}
