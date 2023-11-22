package com.retotecnico.reto.domain.entities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("exchange_rate")
public class ExchangeRateEntity {
  @Id
  private String id;

  private String currencyFrom;

  private String currencyTo;

  private BigDecimal rate;
}

