package com.retotecnico.reto.security.config;

import com.retotecnico.reto.security.service.JWTService;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class AuthManager implements ReactiveAuthenticationManager {

  final JWTService jwtService;
  final ReactiveUserDetailsService users;

  public AuthManager(JWTService jwtService, ReactiveUserDetailsService users) {
    this.jwtService = jwtService;
    this.users = users;
  }


  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    return   Mono.justOrEmpty(
        authentication
    ).cast(BearerToken.class)
        .flatMap(auth -> {
         String getUserName = jwtService.getUserName(auth.getCredentials());
         Mono<UserDetails> foundUser = users.findByUsername(getUserName).defaultIfEmpty(
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
            //confirmar si el user fue encontrado
          return foundUser.<Authentication>flatMap(u-> {
            if (u.getUsername() == null){
              Mono.error(new IllegalArgumentException("Usurario no encontrado en el authManager"));

            }
            if (jwtService.validate(u,auth.getCredentials())){
              return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(u.getUsername(),u.getPassword(),u.getAuthorities()));

            }
            Mono.error( new IllegalArgumentException("Invalido o token expirado"));
            return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(), u.getAuthorities()));
          });
        });


  }
}
