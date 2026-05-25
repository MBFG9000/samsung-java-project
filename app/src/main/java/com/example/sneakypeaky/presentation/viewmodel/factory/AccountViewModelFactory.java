package com.sneakypeaky.presentation.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sneakypeaky.domain.usecase.GetCurrentUserUseCase;
import com.sneakypeaky.domain.usecase.RegisterUseCase;
import com.sneakypeaky.domain.usecase.SignInUseCase;
import com.sneakypeaky.domain.usecase.SignOutUseCase;
import com.sneakypeaky.presentation.viewmodel.AccountViewModel;

public class AccountViewModelFactory implements ViewModelProvider.Factory {
    private final SignInUseCase signInUseCase;
    private final RegisterUseCase registerUseCase;
    private final SignOutUseCase signOutUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    public AccountViewModelFactory(SignInUseCase signInUseCase,
                                   RegisterUseCase registerUseCase,
                                   SignOutUseCase signOutUseCase,
                                   GetCurrentUserUseCase getCurrentUserUseCase) {
        this.signInUseCase = signInUseCase;
        this.registerUseCase = registerUseCase;
        this.signOutUseCase = signOutUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            return (T) new AccountViewModel(signInUseCase, registerUseCase, signOutUseCase, getCurrentUserUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
