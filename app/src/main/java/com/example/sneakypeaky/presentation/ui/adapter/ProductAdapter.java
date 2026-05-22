package com.sneakypeaky.presentation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sneakypeaky.R;
import com.sneakypeaky.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<Product> items = new ArrayList<>();
    private final ProductClickListener listener;

    public ProductAdapter(ProductClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Product> products) {
        items.clear();
        if (products != null) {
            items.addAll(products);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = items.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText(holder.itemView.getContext().getString(R.string.product_price_format, product.getPrice()));
        Glide.with(holder.itemView.getContext()).load(product.getImageUrl()).into(holder.image);
        holder.itemView.setOnClickListener(v -> listener.onProductClicked(product.getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView title;
        final TextView price;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.product_price);
        }
    }

    public interface ProductClickListener {
        void onProductClicked(int productId);
    }
}
