package com.retotecnico.reto.domain.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExchangeRateRequest {
  private String currencyFrom;
  private String currencyTo;
  private BigDecimal newRate;

}
