package com.sneakypeaky.data.repository;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.repository.AuthRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthRepository implements AuthRepository {
    private final FirebaseAuth auth;

    public FirebaseAuthRepository(FirebaseAuth auth) {
        this.auth = auth;
    }

    @Override
    public void signIn(String email, String password, ResultCallback<String> callback) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                FirebaseUser user = authResult.getUser();
                callback.onSuccess(user != null ? user.getEmail() : "");
            })
            .addOnFailureListener(e -> callback.onError(e.getMessage() != null ? e.getMessage() : "Sign-in failed"));
    }

    @Override
    public void register(String email, String password, ResultCallback<String> callback) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                FirebaseUser user = authResult.getUser();
                callback.onSuccess(user != null ? user.getEmail() : "");
            })
            .addOnFailureListener(e -> callback.onError(e.getMessage() != null ? e.getMessage() : "Registration failed"));
    }

    @Override
    public void signOut() {
        auth.signOut();
    }

    @Override
    public String getCurrentUserEmail() {
        FirebaseUser user = auth.getCurrentUser();
        return user != null ? user.getEmail() : null;
    }
}
