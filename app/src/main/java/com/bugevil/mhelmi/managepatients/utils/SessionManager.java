package com.bugevil.mhelmi.managepatients.utils;

import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
  private static final String USERNAME = "username";
  private static final String MAX_PATIENTS_NUMBER = "max_patients_number";
  private static final String CURRENT_PATIENTS_COUNT = "current_patients_count";

  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor editor;

  @Inject
  public SessionManager(SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
    this.sharedPreferences = sharedPreferences;
    this.editor = editor;
  }

  public String getUsername() {
    return sharedPreferences.getString(USERNAME, "");
  }

  public void setUsername(String username) {
    editor.putString(USERNAME, username).apply();
  }

  public int getMaxNumberOfPatients() {
    return sharedPreferences.getInt(MAX_PATIENTS_NUMBER, 5);
  }

  public void setMaxNumberOfPatients(int maxNumberOfPatients) {
    editor.putInt(MAX_PATIENTS_NUMBER, maxNumberOfPatients).apply();
  }

  public int getCurrentPatientsCount() {
    return sharedPreferences.getInt(CURRENT_PATIENTS_COUNT, 0);
  }

  public void setCurrentPatientsCount(int currentPatientsCount) {
    editor.putInt(CURRENT_PATIENTS_COUNT, currentPatientsCount).apply();
  }

  public void increaseCurrentPatientsCount() {
    setCurrentPatientsCount(getCurrentPatientsCount() + 1);
  }

  public void clearData() {
    editor.clear().apply();
  }
}
