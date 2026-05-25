package com.sneakypeaky.domain.usecase;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.repository.AuthRepository;

public class RegisterUseCase {
    private final AuthRepository repository;

    public RegisterUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public void execute(String email, String password, ResultCallback<String> callback) {
        repository.register(email, password, callback);
    }
}
