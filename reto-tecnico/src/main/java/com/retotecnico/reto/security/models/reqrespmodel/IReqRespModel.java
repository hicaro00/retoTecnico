package com.retotecnico.reto.security.models.reqrespmodel;

public interface IReqRespModel<T> {

  T getData();
  String getMessage();//retorna un mensaje  si hay algun error
}
