package com.abromand.mweapp.data.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class VerificationToken {
  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String token;

  @OneToOne(targetEntity = MweUser.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private MweUser user;

  private Date expiryDate;

  private Date calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
  }

  public VerificationToken() {
  }

  public VerificationToken(Long id, String token, MweUser user, Date expiryDate) {
    this.id = id;
    this.token = token;
    this.user = user;
    this.expiryDate = expiryDate;
  }

  public static int getEXPIRATION() {
    return EXPIRATION;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public MweUser getUser() {
    return user;
  }

  public void setUser(MweUser user) {
    this.user = user;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
}
