package com.bugevil.mhelmi.managepatients.features.home.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Patient {
  @PrimaryKey(autoGenerate = true) private int id;
  private String fullName;
  private String email;
  private int age;
  private char gender;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Patient)) return false;
    Patient patient = (Patient) o;
    if (id != patient.id) return false;
    if (age != patient.age) return false;
    if (gender != patient.gender) return false;
    if (!fullName.equals(patient.fullName)) return false;
    return email.equals(patient.email);
  }

  @NonNull @Override public String toString() {
    return "Patient{" +
      "id=" + id +
      ", fullName='" + fullName + '\'' +
      ", gender=" + gender +
      ", age=" + age +
      '}';
  }
}
