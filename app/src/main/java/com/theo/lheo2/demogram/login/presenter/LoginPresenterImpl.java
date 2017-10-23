package com.theo.lheo2.demogram.login.presenter;

import com.theo.lheo2.demogram.login.interactor.LoginInteractor;
import com.theo.lheo2.demogram.login.interactor.LoginInteractorImpl;
import com.theo.lheo2.demogram.login.view.LoginView;

/**
 * Created by theo on 22/10/17.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password) {
        loginView.disableInputs();
        loginView.showProgressBar();
        interactor.signIn(username, password);
    }

    @Override
    public void loginSuccess() {
        loginView.goHome();
        loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }
}
