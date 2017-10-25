package com.theo.lheo2.demogram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by theo on 22/10/17.
 */

public interface LoginInteractor {
    void signIn(String username, String passwors, Activity activity, FirebaseAuth firebaseAuth);
}
