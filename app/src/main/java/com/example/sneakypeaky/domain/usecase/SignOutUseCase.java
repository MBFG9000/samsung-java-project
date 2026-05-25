package com.sneakypeaky.domain.usecase;

import com.sneakypeaky.domain.repository.AuthRepository;

public class SignOutUseCase {
    private final AuthRepository repository;

    public SignOutUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.signOut();
    }
}
