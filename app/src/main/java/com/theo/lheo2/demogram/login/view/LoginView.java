package com.theo.lheo2.demogram.login.view;

import android.view.View;

/**
 * Created by theo on 22/10/17.
 */

public interface LoginView {
    void enableInputs();

    void disableInputs();

    void showProgressBar();

    void hideProgressBar();

    void loginError(String error);

    void goCreateAccount();

    void goHome();
}
