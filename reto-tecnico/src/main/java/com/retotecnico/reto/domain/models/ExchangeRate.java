package com.retotecnico.reto.domain.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRate {

  private String id;
  private String currencyFrom;
  private String currencyTo;
  private BigDecimal rate;
}
