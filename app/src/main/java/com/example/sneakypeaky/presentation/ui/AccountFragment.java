package com.sneakypeaky.presentation.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sneakypeaky.R;
import com.sneakypeaky.data.RepositoryProvider;
import com.sneakypeaky.domain.usecase.GetCurrentUserUseCase;
import com.sneakypeaky.domain.usecase.RegisterUseCase;
import com.sneakypeaky.domain.usecase.SignInUseCase;
import com.sneakypeaky.domain.usecase.SignOutUseCase;
import com.sneakypeaky.presentation.viewmodel.AccountViewModel;
import com.sneakypeaky.presentation.viewmodel.factory.AccountViewModelFactory;

public class AccountFragment extends Fragment {
    private AccountViewModel viewModel;
    private EditText emailInput;
    private EditText passwordInput;
    private TextView statusView;
    private TextView errorView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        emailInput = view.findViewById(R.id.account_email);
        passwordInput = view.findViewById(R.id.account_password);
        statusView = view.findViewById(R.id.account_status);
        errorView = view.findViewById(R.id.account_error);
        progressBar = view.findViewById(R.id.account_progress);

        Button loginButton = view.findViewById(R.id.account_login);
        Button registerButton = view.findViewById(R.id.account_register);
        Button signOutButton = view.findViewById(R.id.account_sign_out);

        AccountViewModelFactory factory = new AccountViewModelFactory(
            new SignInUseCase(RepositoryProvider.getAuthRepository()),
            new RegisterUseCase(RepositoryProvider.getAuthRepository()),
            new SignOutUseCase(RepositoryProvider.getAuthRepository()),
            new GetCurrentUserUseCase(RepositoryProvider.getAuthRepository())
        );
        viewModel = new ViewModelProvider(this, factory).get(AccountViewModel.class);

        viewModel.getUserEmail().observe(getViewLifecycleOwner(), email -> {
            if (email == null || email.isEmpty()) {
                statusView.setText(R.string.account_signed_out);
                signOutButton.setVisibility(View.GONE);
            } else {
                statusView.setText(getString(R.string.account_signed_in_as, email));
                signOutButton.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getError().observe(getViewLifecycleOwner(), message -> {
            if (message != null && !message.isEmpty()) {
                errorView.setText(message);
                errorView.setVisibility(View.VISIBLE);
            } else {
                errorView.setVisibility(View.GONE);
            }
        });

        viewModel.getLoading().observe(getViewLifecycleOwner(), loading -> progressBar.setVisibility(loading ? View.VISIBLE : View.GONE));

        loginButton.setOnClickListener(v -> submit(AuthAction.SIGN_IN));
        registerButton.setOnClickListener(v -> submit(AuthAction.REGISTER));
        signOutButton.setOnClickListener(v -> viewModel.signOut());

        return view;
    }

    private void submit(AuthAction action) {
        errorView.setVisibility(View.GONE);
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            errorView.setText(R.string.account_error_empty);
            errorView.setVisibility(View.VISIBLE);
            return;
        }
        if (action == AuthAction.SIGN_IN) {
            viewModel.signIn(email, password);
        } else {
            viewModel.register(email, password);
        }
    }

    private enum AuthAction {
        SIGN_IN,
        REGISTER
    }
}
