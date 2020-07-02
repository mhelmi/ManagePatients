package com.bugevil.mhelmi.managepatients.di;

import com.bugevil.mhelmi.managepatients.App;

public class Injector {
  public static AppComponent get() {
    return App.getInstance().getAppComponent();
  }
}
