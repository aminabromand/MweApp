package com.abromand.mweapp.data.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@Entity
public class VerificationToken {
  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String tokenString;

  @OneToOne(targetEntity = MweUser.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private MweUser user;

  @Column
  private Date expiryDate;

  private Date calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
  }

  public Date getStandardExpiryDate() {
    return calculateExpiryDate(EXPIRATION);
  }

  public boolean isExpired() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    Date currentDate = new Date(cal.getTime().getTime());
    return currentDate.before(expiryDate);
  }

  public VerificationToken() {
  }

  public VerificationToken(Long id, String tokenString, MweUser user, Date expiryDate) {
    this.id = id;
    this.tokenString = tokenString;
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

  public String getTokenString() {
    return tokenString;
  }

  public void setTokenString(String token) {
    this.tokenString = token;
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
