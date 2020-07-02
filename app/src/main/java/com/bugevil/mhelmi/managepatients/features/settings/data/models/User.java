package com.bugevil.mhelmi.managepatients.features.settings.data.models;

public class User {
  private String username;
  private int maxNumberOfPatients;
  private int currentPatientsCount;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getMaxNumberOfPatients() {
    return maxNumberOfPatients;
  }

  public void setMaxNumberOfPatients(int maxNumberOfPatients) {
    this.maxNumberOfPatients = maxNumberOfPatients;
  }

  public int getCurrentPatientsCount() {
    return currentPatientsCount;
  }

  public void setCurrentPatientsCount(int currentPatientsCount) {
    this.currentPatientsCount = currentPatientsCount;
  }
}
