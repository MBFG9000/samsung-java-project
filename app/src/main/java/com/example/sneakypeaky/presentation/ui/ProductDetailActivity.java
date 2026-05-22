package com.sneakypeaky.presentation.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.sneakypeaky.R;
import com.sneakypeaky.data.RepositoryProvider;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.usecase.GetProductUseCase;
import com.sneakypeaky.presentation.viewmodel.ProductDetailViewModel;
import com.sneakypeaky.presentation.viewmodel.factory.ProductDetailViewModelFactory;

public class ProductDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PRODUCT_ID = "extra_product_id";

    private ProductDetailViewModel viewModel;
    private ProgressBar progressBar;
    private TextView errorView;
    private ImageView imageView;
    private TextView titleView;
    private TextView priceView;
    private TextView descriptionView;
    private Button addToCartButton;
    private Product currentProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        progressBar = findViewById(R.id.detail_progress);
        errorView = findViewById(R.id.detail_error);
        imageView = findViewById(R.id.detail_image);
        titleView = findViewById(R.id.detail_title);
        priceView = findViewById(R.id.detail_price);
        descriptionView = findViewById(R.id.detail_description);
        addToCartButton = findViewById(R.id.detail_add_to_cart);

        int productId = getIntent().getIntExtra(EXTRA_PRODUCT_ID, -1);
        if (productId == -1) {
            errorView.setText(R.string.product_not_found);
            errorView.setVisibility(View.VISIBLE);
            return;
        }

        ProductDetailViewModelFactory factory = new ProductDetailViewModelFactory(
            new GetProductUseCase(RepositoryProvider.getProductRepository())
        );
        viewModel = new ViewModelProvider(this, factory).get(ProductDetailViewModel.class);

        viewModel.getProduct().observe(this, product -> {
            currentProduct = product;
            if (product != null) {
                titleView.setText(product.getTitle());
                priceView.setText(getString(R.string.product_price_format, product.getPrice()));
                descriptionView.setText(product.getDescription());
                Glide.with(this).load(product.getImageUrl()).into(imageView);
            }
        });
        viewModel.getLoading().observe(this, loading -> progressBar.setVisibility(loading ? View.VISIBLE : View.GONE));
        viewModel.getError().observe(this, message -> {
            if (message != null && !message.isEmpty()) {
                errorView.setText(message);
                errorView.setVisibility(View.VISIBLE);
            } else {
                errorView.setVisibility(View.GONE);
            }
        });

        addToCartButton.setOnClickListener(v -> {
            if (currentProduct != null) {
                RepositoryProvider.getCartRepository().addToCart(currentProduct);
            }
        });

        viewModel.loadProduct(productId);
    }
}
