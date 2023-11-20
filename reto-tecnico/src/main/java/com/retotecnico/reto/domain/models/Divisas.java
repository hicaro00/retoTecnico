package com.retotecnico.reto.domain.models;

public enum Divisas {
  USD("Dólar estadounidense"),
  EUR("Euro"),
  JPY("Yen japonés"),
  GBP("Libra esterlina"),
  AUD("Dólar australiano"),
  CAD("Dólar canadiense"),
  CHF("Franco suizo"),
  CNY("Yuan chino"),
  SEK("Corona sueca"),
  NZD("Dólar neozelandés");

  private final String nombre;


  Divisas(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

}
