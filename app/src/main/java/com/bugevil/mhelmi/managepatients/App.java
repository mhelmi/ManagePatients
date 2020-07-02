package com.bugevil.mhelmi.managepatients;

import android.app.Application;
import com.bugevil.mhelmi.managepatients.di.AppComponent;
import com.bugevil.mhelmi.managepatients.di.ContextModule;
import com.bugevil.mhelmi.managepatients.di.DaggerAppComponent;

public class App extends Application {
  private static App INSTANCE = null;
  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    INSTANCE = this;
    appComponent = DaggerAppComponent.factory()
      .create(new ContextModule(this));
  }

  public static App getInstance() {
    return INSTANCE;
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
