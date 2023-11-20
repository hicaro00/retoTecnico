package com.retotecnico.reto.domain.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResult {
  private BigDecimal amount;
  private BigDecimal convertedAmount;
  private String currencyFrom;
  private String currencyTo;
  private BigDecimal exchangeRate;
}
