package com.abromand.mweapp.data.dto;

public class VerificationTokenDto {

  private String tokenString;

  private String email;

  public VerificationTokenDto() {
  }

  public VerificationTokenDto(String tokenString, String email) {
    this.tokenString = tokenString;
    this.email = email;
  }

  public String getTokenString() {
    return tokenString;
  }

  public void setTokenString(String tokenString) {
    this.tokenString = tokenString;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
