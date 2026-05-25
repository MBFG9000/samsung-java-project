package com.sneakypeaky.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sneakypeaky.R;
import com.sneakypeaky.data.RepositoryProvider;
import com.sneakypeaky.presentation.ui.adapter.CartAdapter;
import com.sneakypeaky.presentation.viewmodel.CartViewModel;
import com.sneakypeaky.presentation.viewmodel.factory.CartViewModelFactory;

public class CartFragment extends Fragment implements CartAdapter.CartItemListener {
    private CartViewModel viewModel;
    private CartAdapter adapter;
    private TextView totalView;
    private TextView emptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.cart_list);
        totalView = view.findViewById(R.id.cart_total);
        emptyView = view.findViewById(R.id.cart_empty);
        Button clearButton = view.findViewById(R.id.cart_clear);

        adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        CartViewModelFactory factory = new CartViewModelFactory(RepositoryProvider.getCartRepository());
        viewModel = new ViewModelProvider(this, factory).get(CartViewModel.class);
        viewModel.getCartItems().observe(getViewLifecycleOwner(), items -> {
            adapter.submitList(items);
            boolean empty = items == null || items.isEmpty();
            emptyView.setVisibility(empty ? View.VISIBLE : View.GONE);
            totalView.setText(getString(R.string.cart_total_format, viewModel.getTotalPrice()));
        });

        clearButton.setOnClickListener(v -> viewModel.clearCart());

        return view;
    }

    @Override
    public void onRemoveClicked(int productId) {
        viewModel.removeFromCart(productId);
    }
}
