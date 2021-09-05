package com.abromand.mweapp.data.dto;

public class MweUserDto {

  private Long id;

  private String username;
  private String password;
  private String email;
  private String description;
  private String address;
  private String phone;
  private String occupation;

  private boolean cto;
  private boolean csb;

  private int ssbcount;

  public MweUserDto() {

  }

  public MweUserDto(Long id, String username, String password, String email, boolean cto, boolean csb, String description, String address, String phone, int ssbcount, String occupation) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.cto = cto;
    this.csb = csb;
    this.description = description;
    this.address = address;
    this.phone = phone;
    this.ssbcount = ssbcount;
    this.occupation = occupation;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getSsbcount() {
    return ssbcount;
  }

  public void setSsbcount(int ssbcount) {
    this.ssbcount = ssbcount;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }
}
