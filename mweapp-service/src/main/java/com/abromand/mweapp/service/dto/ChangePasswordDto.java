package com.abromand.mweapp.service.dto;

import com.abromand.mweapp.service.validation.PasswordMatches;

@PasswordMatches
public class ChangePasswordDto {

  private String password;
  private String matchingPassword;

  public ChangePasswordDto() {
  }

  public ChangePasswordDto(String password, String matchingPassword) {
    this.password = password;
    this.matchingPassword = matchingPassword;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }
}
