package com.sneakypeaky.domain.repository;

import com.sneakypeaky.domain.common.ResultCallback;

public interface AuthRepository {
    void signIn(String email, String password, ResultCallback<String> callback);

    void register(String email, String password, ResultCallback<String> callback);

    void signOut();

    String getCurrentUserEmail();
}
