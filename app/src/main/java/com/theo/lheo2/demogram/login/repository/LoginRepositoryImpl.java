package com.theo.lheo2.demogram.login.repository;

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
    public void signIn(String username, String password) {
        boolean success = true;
        if (success) {
            presenter.loginSuccess();
        } else {
            presenter.loginError("Ocurrió un Error");
        }
    }
}
