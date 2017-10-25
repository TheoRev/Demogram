package com.theo.lheo2.demogram.login.repository;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.theo.lheo2.demogram.login.presenter.LoginPresenter;

/**
 * Created by theo on 22/10/17.
 */

public class LoginRepositoryImpl implements LoginRepository {

    LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {

        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         presenter.loginSuccess();
                     }else {
                         presenter.loginError("Ocurrio un error");
                     }
                    }
                });

//        boolean success = true;
//        if (success) {
//            presenter.loginSuccess();
//        } else {
//            presenter.loginError("Ocurrió un Error");
//        }
    }
}
