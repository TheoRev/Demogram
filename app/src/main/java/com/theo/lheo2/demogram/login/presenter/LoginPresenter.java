package com.theo.lheo2.demogram.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by theo on 22/10/17.
 */

public interface LoginPresenter {
    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);// Interactor

    void loginSuccess();

    void loginError(String error);
}
