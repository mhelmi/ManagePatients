package com.bugevil.mhelmi.managepatients.features.home.ui.viewmodels;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import com.bugevil.mhelmi.managepatients.features.home.domain.AddPatientUseCase;
import com.bugevil.mhelmi.managepatients.features.home.domain.DeleteAllPatientsUseCase;
import com.bugevil.mhelmi.managepatients.features.home.domain.GetPatientsUseCase;
import com.bugevil.mhelmi.managepatients.utils.viewmodel.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class PatientsViewModel extends BaseViewModel {
  private static final String TAG = PatientsViewModel.class.getSimpleName();
  private GetPatientsUseCase getPatientsUseCase;
  private AddPatientUseCase addPatientUseCase;
  private DeleteAllPatientsUseCase deleteAllPatientsUseCase;

  private MutableLiveData<List<Patient>> patientsResult = new MutableLiveData<>();

  @Inject public PatientsViewModel(
    GetPatientsUseCase getPatientsUseCase,
    AddPatientUseCase addPatientUseCase,
    DeleteAllPatientsUseCase deleteAllPatientsUseCase
  ) {
    this.getPatientsUseCase = getPatientsUseCase;
    this.addPatientUseCase = addPatientUseCase;
    this.deleteAllPatientsUseCase = deleteAllPatientsUseCase;
  }

  public LiveData<List<Patient>> getPatientsResult() {
    return patientsResult;
  }

  public void loadPatients() {
    add(getPatientsUseCase.get()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(patientsResult::postValue, t -> Log.e(TAG, "getPatients: ", t))
    );
  }

  public void addPatient(Patient patient) {
    add(addPatientUseCase.excute(patient)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(() -> Log.e(TAG, "addPatient: success"), t -> Log.e(TAG, "addPatient: ", t))
    );
  }

  public void deleteAllPatients() {
    add(deleteAllPatientsUseCase.excute()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(() -> Log.e(TAG, "deleteAllPatients: success"),
        t -> Log.e(TAG, "deleteAllPatients: ", t))
    );
  }
}