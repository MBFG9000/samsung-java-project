package com.sneakypeaky.domain.usecase;

import com.sneakypeaky.domain.repository.AuthRepository;

public class GetCurrentUserUseCase {
    private final AuthRepository repository;

    public GetCurrentUserUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public String execute() {
        return repository.getCurrentUserEmail();
    }
}
