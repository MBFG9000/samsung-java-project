package com.sneakypeaky.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.usecase.GetCurrentUserUseCase;
import com.sneakypeaky.domain.usecase.RegisterUseCase;
import com.sneakypeaky.domain.usecase.SignInUseCase;
import com.sneakypeaky.domain.usecase.SignOutUseCase;

public class AccountViewModel extends ViewModel {
    private final SignInUseCase signInUseCase;
    private final RegisterUseCase registerUseCase;
    private final SignOutUseCase signOutUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    private final MutableLiveData<String> userEmail = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public AccountViewModel(SignInUseCase signInUseCase,
                            RegisterUseCase registerUseCase,
                            SignOutUseCase signOutUseCase,
                            GetCurrentUserUseCase getCurrentUserUseCase) {
        this.signInUseCase = signInUseCase;
        this.registerUseCase = registerUseCase;
        this.signOutUseCase = signOutUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        refreshUser();
    }

    public LiveData<String> getUserEmail() {
        return userEmail;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void signIn(String email, String password) {
        loading.setValue(true);
        signInUseCase.execute(email, password, new ResultCallback<String>() {
            @Override
            public void onSuccess(String data) {
                loading.postValue(false);
                error.postValue(null);
                userEmail.postValue(data);
            }

            @Override
            public void onError(String message) {
                loading.postValue(false);
                error.postValue(message);
            }
        });
    }

    public void register(String email, String password) {
        loading.setValue(true);
        registerUseCase.execute(email, password, new ResultCallback<String>() {
            @Override
            public void onSuccess(String data) {
                loading.postValue(false);
                error.postValue(null);
                userEmail.postValue(data);
            }

            @Override
            public void onError(String message) {
                loading.postValue(false);
                error.postValue(message);
            }
        });
    }

    public void signOut() {
        signOutUseCase.execute();
        refreshUser();
    }

    public void refreshUser() {
        userEmail.setValue(getCurrentUserUseCase.execute());
    }
}
