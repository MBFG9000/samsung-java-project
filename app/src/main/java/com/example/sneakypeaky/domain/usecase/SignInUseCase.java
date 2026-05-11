package com.sneakypeaky.domain.usecase;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.repository.AuthRepository;

public class SignInUseCase {
    private final AuthRepository repository;

    public SignInUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public void execute(String email, String password, ResultCallback<String> callback) {
        repository.signIn(email, password, callback);
    }
}
