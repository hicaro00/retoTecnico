package com.retotecnico.reto.domain.dtos;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRateDto {
  private String id;
  private String currencyFrom;
  private String currencyTo;
  private BigDecimal rate;
}
