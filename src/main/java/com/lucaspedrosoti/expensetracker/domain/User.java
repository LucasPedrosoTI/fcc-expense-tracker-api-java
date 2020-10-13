package com.lucaspedrosoti.expensetracker.domain;

import java.util.Objects;

public class User {

  private Integer userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;

  public User() {
  }

  public User(Integer userId, String firstName, String lastName, String email, String password) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(userId, user.userId) && Objects.equals(firstName, user.firstName)
        && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email)
        && Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, firstName, lastName, email, password);
  }

  @Override
  public String toString() {
    return "{" + " userId='" + getUserId() + "'" + ", firstName='" + getFirstName() + "'" + ", lastName='"
        + getLastName() + "'" + ", email='" + getEmail() + "'" + ", password='" + getPassword() + "'" + "}";
  }

}
