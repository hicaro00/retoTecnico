package com.retotecnico.reto.domain.models;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConversionRequest {
  private BigDecimal amount;
  private String currencyFrom;
  private String currencyTo;
}
