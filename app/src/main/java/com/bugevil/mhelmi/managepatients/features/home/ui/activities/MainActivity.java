package com.bugevil.mhelmi.managepatients.features.home.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bugevil.mhelmi.managepatients.R;
import com.bugevil.mhelmi.managepatients.databinding.ActivityMainBinding;
import com.bugevil.mhelmi.managepatients.di.Injector;
import com.bugevil.mhelmi.managepatients.features.home.ui.viewmodels.PatientsViewModel;
import com.bugevil.mhelmi.managepatients.features.settings.ui.SettingsViewModel;
import com.bugevil.mhelmi.managepatients.utils.ShareUtils;
import com.bugevil.mhelmi.managepatients.utils.viewmodel.ViewModelFactory;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = MainActivity.class.getSimpleName();
  private ActivityMainBinding binding;
  private FragmentActivity activity;
  private Context context;
  private NavController navController;
  private AppBarConfiguration mAppBarConfiguration;
  private PatientsViewModel patientsViewModel;
  private SettingsViewModel settingsViewModel;
  @Inject ViewModelFactory viewModelFactory;
  private String currentPatientsCount;
  private String maxNumberOfPatients;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activity = this;
    context = this;
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    Injector.get().inject(this);
    setContentView(binding.getRoot());
    setSupportActionBar(binding.appBar.toolbar);
    setupNavigation();
    initViewModels();
    setObservers();
  }

  private void setupNavigation() {
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    mAppBarConfiguration = new AppBarConfiguration.Builder(
      R.id.homeFragment, R.id.settingsFragment)
      .setDrawerLayout(binding.drawerLayout)
      .build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(binding.navView, navController);
    setNavSelectedItemListener();
  }

  private void setNavSelectedItemListener() {
    binding.navView.setNavigationItemSelectedListener(item -> {
      Log.e(TAG, "setNavSelectedItemListener:");
      switch (item.getItemId()) {
        case R.id.homeFragment:
          navController.navigate(R.id.action_navigate_to_homeFragment);
          break;
        case R.id.settingsFragment:
          navController.navigate(R.id.action_navigate_to_settingsFragment);
          break;
        case R.id.nav_share:
          ShareUtils.shareText(context,
            getString(R.string.share_you_result, currentPatientsCount, maxNumberOfPatients));
          break;
      }
      binding.drawerLayout.closeDrawer(GravityCompat.START);
      return true;
    });
  }

  private void initViewModels() {
    patientsViewModel =
      new ViewModelProvider(activity, viewModelFactory).get(PatientsViewModel.class);
    settingsViewModel =
      new ViewModelProvider(activity, viewModelFactory).get(SettingsViewModel.class);
  }

  private void setObservers() {
    settingsViewModel.getUsername().observe(this, this::setupNavHeader);
    settingsViewModel.getCurrentPatientsCount().observe(this, s -> currentPatientsCount = s);
    settingsViewModel.getMaxNumberOfPatients().observe(this, s -> maxNumberOfPatients = s);
  }

  private void setupNavHeader(String username) {
    TextView tvUsername = binding.navView.getHeaderView(0).findViewById(R.id.tvUsername);
    tvUsername.setText(username);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.action_reset) {
      patientsViewModel.deleteAllPatients();
      settingsViewModel.clearSettings();
    }
    return NavigationUI.onNavDestinationSelected(item, navController)
      || super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onSupportNavigateUp() {
    return NavigationUI.navigateUp(navController, mAppBarConfiguration)
      || super.onSupportNavigateUp();
  }
}