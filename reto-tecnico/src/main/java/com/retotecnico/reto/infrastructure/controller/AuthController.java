package com.retotecnico.reto.infrastructure.controller;

import com.retotecnico.reto.security.models.reqrespbodies.ReqLogin;
import com.retotecnico.reto.security.models.reqrespmodel.ReqRespModel;
import com.retotecnico.reto.security.service.JWTService;
import java.util.Collection;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

  final ReactiveUserDetailsService users;

  final JWTService jwtService;

  final PasswordEncoder passwordEncoder;

  public AuthController(ReactiveUserDetailsService users, JWTService jwtService,
                        PasswordEncoder passwordEncoder) {
    this.users = users;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
  }


  @GetMapping("/auth")
  public Mono<ResponseEntity<ReqRespModel<String>>> auth() {

    return Mono.just(
        ResponseEntity.ok(
            new ReqRespModel<>("Hola bienvenido ", "")
        )
    );
  }

  @PostMapping("/login")
  public Mono<ResponseEntity<ReqRespModel<String>>> login(@RequestBody ReqLogin user) {
    Mono<UserDetails> foundUser = users.findByUsername(user.getUser()).defaultIfEmpty(
        new UserDetails() {
          @Override
          public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
          }

          @Override
          public String getPassword() {
            return null;
          }

          @Override
          public String getUsername() {
            return null;
          }

          @Override
          public boolean isAccountNonExpired() {
            return false;
          }

          @Override
          public boolean isAccountNonLocked() {
            return false;
          }

          @Override
          public boolean isCredentialsNonExpired() {
            return false;
          }

          @Override
          public boolean isEnabled() {
            return false;
          }
        });

    return foundUser.map(
        u -> {
          if (u.getUsername() == null) {
            return ResponseEntity.status(404).body(new ReqRespModel<>("",
                "usuario no encontrado por favor registrese antes de logearse"));
          }
          if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
            return ResponseEntity.ok(
                new ReqRespModel<>(jwtService.generate(u.getUsername()), "exito")
            );
          }
          return ResponseEntity.badRequest().body(new ReqRespModel<>("", "credenciales invalidas"));
        }
    );


  }


}
