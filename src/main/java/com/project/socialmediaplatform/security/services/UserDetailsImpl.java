package com.project.socialmediaplatform.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.socialmediaplatform.model.User;

public class UserDetailsImpl implements UserDetails {

  private Long id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  public UserDetailsImpl(Long id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public static UserDetailsImpl build(User user) {

    return new UserDetailsImpl(
        user.getUserId(),
        user.getEmail(),
        user.getPassword());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
