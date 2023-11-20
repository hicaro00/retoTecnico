package com.retotecnico.reto.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
  final private SecretKey key;
  final private JwtParser parser;

//Keys.hmacShaKeyFor("shdfhsfdghsfghsfghghsererfsdssfgfhfhfhhghh".getBytes())

  public JWTService() {
    this.key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256) ;
    this.parser = Jwts.parserBuilder().setSigningKey(this.key).build();
  }

  // estoe s para retornar un toquen basado en el usuario
  public String generate(String userName){
    JwtBuilder builder = Jwts.builder()
        .setSubject(userName)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
        .signWith(key);
    return builder.compact();
  }

  public String getUserName(String token){
    Claims claims = parser.parseClaimsJws(token).getBody();
    return  claims.getSubject();
  }

  public Boolean validate(UserDetails user, String token){
    Claims claims = parser.parseClaimsJws(token).getBody();
    boolean unexpires = claims.getExpiration().after(Date.from(Instant.now()));
    return unexpires && user.getUsername() .equals(claims.getSubject()) ;
  }

}
