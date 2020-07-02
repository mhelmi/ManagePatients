package com.bugevil.mhelmi.managepatients.features.settings.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bugevil.mhelmi.managepatients.utils.SessionManager;
import com.bugevil.mhelmi.managepatients.utils.viewmodel.BaseViewModel;
import javax.inject.Inject;

public class SettingsViewModel extends BaseViewModel {

  private SessionManager sessionManager;

  private MutableLiveData<String> username = new MutableLiveData<>();
  private MutableLiveData<String> maxNumberOfPatients = new MutableLiveData<>();
  private MutableLiveData<String> currentPatientsCount = new MutableLiveData<>();

  @Inject
  public SettingsViewModel(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
    loadSettings();
  }

  public void loadSettings() {
    username.postValue(sessionManager.getUsername());
    maxNumberOfPatients.postValue(String.valueOf(sessionManager.getMaxNumberOfPatients()));
    currentPatientsCount.postValue(String.valueOf(sessionManager.getCurrentPatientsCount()));
  }

  public void saveUserSettings(String username, int maxNumberOfPatients) {
    sessionManager.setUsername(username);
    sessionManager.setMaxNumberOfPatients(maxNumberOfPatients);
    loadSettings();
  }

  public LiveData<String> getUsername() {
    return username;
  }

  public LiveData<String> getMaxNumberOfPatients() {
    return maxNumberOfPatients;
  }

  public LiveData<String> getCurrentPatientsCount() {
    return currentPatientsCount;
  }

  public void increaseCurrentPatientsCount() {
    sessionManager.increaseCurrentPatientsCount();
    loadSettings();
  }

  public void clearSettings() {
    sessionManager.clearData();
    loadSettings();
  }
}