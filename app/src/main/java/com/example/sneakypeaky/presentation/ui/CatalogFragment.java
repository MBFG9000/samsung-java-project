package com.sneakypeaky.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sneakypeaky.R;
import com.sneakypeaky.data.RepositoryProvider;
import com.sneakypeaky.domain.usecase.GetProductsUseCase;
import com.sneakypeaky.presentation.ui.adapter.ProductAdapter;
import com.sneakypeaky.presentation.viewmodel.CatalogViewModel;
import com.sneakypeaky.presentation.viewmodel.factory.CatalogViewModelFactory;

public class CatalogFragment extends Fragment implements ProductAdapter.ProductClickListener {
    private CatalogViewModel viewModel;
    private ProductAdapter adapter;
    private ProgressBar progressBar;
    private TextView errorView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.catalog_list);
        progressBar = view.findViewById(R.id.catalog_progress);
        errorView = view.findViewById(R.id.catalog_error);

        adapter = new ProductAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        CatalogViewModelFactory factory = new CatalogViewModelFactory(
            new GetProductsUseCase(RepositoryProvider.getProductRepository())
        );
        viewModel = new ViewModelProvider(this, factory).get(CatalogViewModel.class);
        observeViewModel();
        viewModel.loadProducts();

        return view;
    }

    private void observeViewModel() {
        viewModel.getProducts().observe(getViewLifecycleOwner(), products -> adapter.submitList(products));
        viewModel.getLoading().observe(getViewLifecycleOwner(), loading -> progressBar.setVisibility(loading ? View.VISIBLE : View.GONE));
        viewModel.getError().observe(getViewLifecycleOwner(), message -> {
            if (message != null && !message.isEmpty()) {
                errorView.setText(message);
                errorView.setVisibility(View.VISIBLE);
            } else {
                errorView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onProductClicked(int productId) {
        Intent intent = new Intent(requireContext(), ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, productId);
        startActivity(intent);
    }
}
