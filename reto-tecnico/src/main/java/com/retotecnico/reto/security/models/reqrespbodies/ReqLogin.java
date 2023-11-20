package com.retotecnico.reto.security.models.reqrespbodies;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReqLogin {
  private String user;
  private String password;
}
