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

  @Column
  private boolean cto;

  @Column
  private boolean csb;

  public MweUser() {}

  public MweUser(Long id, String username, String password, boolean cto, boolean csb) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.csb = csb;
    this.cto = cto;
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
