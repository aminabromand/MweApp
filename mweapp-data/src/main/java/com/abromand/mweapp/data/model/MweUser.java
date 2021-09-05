package com.abromand.mweapp.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mwe_user")
public class MweUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column
  private String occupation;

  @Column
  private String description;

  @Column
  private String phone;

  @Column
  private String address;

  @Column
  private boolean cto;

  @Column
  private boolean csb;

  @Column
  private int ssbcount;

  public MweUser() {}

  public MweUser(Long id, String username, String password, String email, boolean cto, boolean csb, String description, String phone, String address, int ssbcount, String occupation) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.csb = csb;
    this.cto = cto;
    this.email = email;
    this.description = description;
    this.phone = phone;
    this.address = address;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public int getSsbcount() {
    return ssbcount;
  }

  public void setSsbcount(int ssbcount) {
    this.ssbcount = ssbcount;
  }
}
