package com.bugevil.mhelmi.managepatients.features.settings.ui;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.bugevil.mhelmi.managepatients.R;
import com.bugevil.mhelmi.managepatients.databinding.FragmentSettingsBinding;
import com.bugevil.mhelmi.managepatients.di.Injector;
import com.bugevil.mhelmi.managepatients.utils.viewmodel.ViewModelFactory;
import java.util.Objects;
import javax.inject.Inject;

public class SettingsFragment extends Fragment {
  private FragmentSettingsBinding binding;
  private FragmentActivity activity;
  private Context context;
  NavController navController;
  private SettingsViewModel settingsViewModel;
  @Inject ViewModelFactory viewModelFactory;

  private String currentUserName;

  public View onCreateView(@NonNull LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {
    activity = getActivity();
    context = getContext();
    binding = FragmentSettingsBinding.inflate(inflater, container, false);
    Injector.get().inject(this);
    setClickListeners();
    initViewModels();
    setObservers();
    navController = Navigation.findNavController(activity, R.id.nav_host_fragment);

    return binding.getRoot();
  }

  private void setClickListeners() {
    binding.btnSave.setOnClickListener(view -> {
      String username = Objects.requireNonNull(binding.etUsername.getText()).toString();
      String maxNumberOfPatients =
        Objects.requireNonNull(binding.etMaxNumberOfPatients.getText()).toString();

      if (TextUtils.isEmpty(username)) {
        binding.etUsername.setError(getString(R.string.error_empty_username));
      } else if (username.equals(currentUserName)) {
        Toast.makeText(context, getString(R.string.hi_again_username_, username), Toast.LENGTH_LONG)
          .show();
      } else if (TextUtils.isEmpty(maxNumberOfPatients)) {
        binding.etUsername.setError(getString(R.string.error_empty_max_number_of_patients));
      } else {
        settingsViewModel.saveUserSettings(username, Integer.parseInt(maxNumberOfPatients));
        navController.popBackStack();
      }
    });
  }

  private void initViewModels() {
    settingsViewModel =
      new ViewModelProvider(activity, viewModelFactory).get(SettingsViewModel.class);
  }

  private void setObservers() {
    settingsViewModel.getUsername()
      .observe(getViewLifecycleOwner(), this::feedUpUserName);
    settingsViewModel.getMaxNumberOfPatients()
      .observe(getViewLifecycleOwner(), binding.etMaxNumberOfPatients::setText);
    settingsViewModel.getCurrentPatientsCount()
      .observe(getViewLifecycleOwner(), binding.etCurrentPatientCount::setText);
  }

  private void feedUpUserName(String username) {
    binding.etUsername.setText(username);
    this.currentUserName = username;
  }
}