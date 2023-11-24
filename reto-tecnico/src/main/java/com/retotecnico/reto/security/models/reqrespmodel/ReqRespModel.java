package com.retotecnico.reto.security.models.reqrespmodel;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class ReqRespModel<T> implements IReqRespModel<T>{
  private T token;

  @Override
  public T getToken() {
    return this.token;
  }


}
