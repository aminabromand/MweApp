package com.abromand.mweapp.data.dto;

public class MweUserDto {

  private Long id;

  private String username;

  private String password;

  private boolean cto;

  private boolean csb;

  public MweUserDto() {

  }

  public MweUserDto(Long id, String username, String password, boolean cto, boolean csb) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.cto = cto;
    this.csb = csb;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isCto() {
    return cto;
  }

  public void setCto(boolean cto) {
    this.cto = cto;
  }

  public boolean isCsb() {
    return csb;
  }

  public void setCsb(boolean csb) {
    this.csb = csb;
  }
}
