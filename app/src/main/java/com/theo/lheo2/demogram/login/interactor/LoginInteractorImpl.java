package com.theo.lheo2.demogram.login.interactor;

import com.theo.lheo2.demogram.login.presenter.LoginPresenter;
import com.theo.lheo2.demogram.login.repository.LoginRepository;
import com.theo.lheo2.demogram.login.repository.LoginRepositoryImpl;

/**
 * Created by theo on 22/10/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter) {
        this.presenter = presenter;
        repository = new LoginRepositoryImpl(presenter);
    }

    @Override
    public void signIn(String username, String passwors) {
        repository.signIn(username, passwors);
    }
}
