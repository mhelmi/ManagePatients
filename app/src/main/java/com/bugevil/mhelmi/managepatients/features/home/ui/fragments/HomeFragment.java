package com.bugevil.mhelmi.managepatients.features.home.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bugevil.mhelmi.managepatients.R;
import com.bugevil.mhelmi.managepatients.databinding.FragmentHomeBinding;
import com.bugevil.mhelmi.managepatients.di.Injector;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import com.bugevil.mhelmi.managepatients.features.home.ui.viewmodels.PatientsViewModel;
import com.bugevil.mhelmi.managepatients.features.home.ui.adapters.PatientsAdapter;
import com.bugevil.mhelmi.managepatients.features.settings.ui.SettingsViewModel;
import com.bugevil.mhelmi.managepatients.utils.viewmodel.ViewModelFactory;
import java.util.Objects;
import javax.inject.Inject;

public class HomeFragment extends Fragment {

  private FragmentHomeBinding binding;
  private FragmentActivity activity;
  private Context context;
  private PatientsViewModel patientsViewModel;
  private SettingsViewModel settingsViewModel;
  @Inject ViewModelFactory viewModelFactory;
  private PatientsAdapter patientsAdapter;
  private int currentPatientsCount;
  private int maxNumberOfPatients;

  public View onCreateView(@NonNull LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {
    activity = getActivity();
    context = getContext();
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    Injector.get().inject(this);
    setupPatientsRecyclerView();
    initViewModels();
    setObservers();
    setClickListeners();
    loadPatients();
    return binding.getRoot();
  }

  private void setClickListeners() {
    binding.fabAddPatient.setOnClickListener(view -> addNewPatient());
  }

  private void initViewModels() {
    patientsViewModel =
      new ViewModelProvider(activity, viewModelFactory).get(PatientsViewModel.class);
    settingsViewModel =
      new ViewModelProvider(activity, viewModelFactory).get(SettingsViewModel.class);
  }

  private void setObservers() {
    patientsViewModel.getPatientsResult()
      .observe(getViewLifecycleOwner(), patientsAdapter::submitList);
    patientsViewModel.getPatientsResult()
      .observe(getViewLifecycleOwner(), patientsAdapter::submitList);
    settingsViewModel.getCurrentPatientsCount()
      .observe(getViewLifecycleOwner(), s -> currentPatientsCount = Integer.parseInt(s));
    settingsViewModel.getMaxNumberOfPatients()
      .observe(getViewLifecycleOwner(), s -> maxNumberOfPatients = Integer.parseInt(s));
  }

  private void loadPatients() {
    patientsViewModel.loadPatients();
  }

  private void setupPatientsRecyclerView() {
    binding.rvPatients.setLayoutManager(new LinearLayoutManager(context));
    patientsAdapter = new PatientsAdapter();
    binding.rvPatients.setAdapter(patientsAdapter);
  }

  private void addNewPatient() {
    if (currentPatientsCount < maxNumberOfPatients) {
      String fullName = Objects.requireNonNull(binding.etFullName.getText()).toString();
      String age = Objects.requireNonNull(binding.etAge.getText()).toString();
      String email = Objects.requireNonNull(binding.etEmail.getText()).toString();
      int selectedGenderId = binding.radioGroupGender.getCheckedRadioButtonId();

      if (TextUtils.isEmpty(fullName)) {
        binding.tilFullName.setError(getString(R.string.error_empty_name));
      } else if (TextUtils.isEmpty(age)) {
        binding.tilAge.setError(getString(R.string.error_empty_age));
      } else if (selectedGenderId == -1) {
        Toast.makeText(context, getString(R.string.error_select_gender), Toast.LENGTH_LONG)
          .show();
      } else if (TextUtils.isEmpty(email)) {
        binding.tilEmail.setError(getString(R.string.error_empty_email));
      } else {
        Patient patient = new Patient();
        patient.setFullName(fullName);
        patient.setGender(getSelectedGender(selectedGenderId));
        patient.setAge(Integer.parseInt(age));
        patient.setEmail(email);
        patientsViewModel.addPatient(patient);
        settingsViewModel.increaseCurrentPatientsCount();
      }
    } else {
      Toast.makeText(context,
        getString(R.string.error_you_have_reach_max_number_of_patients_allowed), Toast.LENGTH_LONG)
        .show();
    }
  }

  private char getSelectedGender(int selectedGenderId) {
    switch (selectedGenderId) {
      case R.id.radioMale:
        return 'M';
      case R.id.radioFemale:
        return 'F';
      default:
        return 'O';
    }
  }
}