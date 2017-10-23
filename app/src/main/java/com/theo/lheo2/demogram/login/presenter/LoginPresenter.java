package com.theo.lheo2.demogram.login.presenter;

/**
 * Created by theo on 22/10/17.
 */

public interface LoginPresenter {
    void signIn(String username, String password);// Interactor

    void loginSuccess();

    void loginError(String error);
}
