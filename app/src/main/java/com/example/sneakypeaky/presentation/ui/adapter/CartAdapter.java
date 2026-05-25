package com.sneakypeaky.presentation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sneakypeaky.R;
import com.sneakypeaky.domain.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final List<CartItem> items = new ArrayList<>();
    private final CartItemListener listener;

    public CartAdapter(CartItemListener listener) {
        this.listener = listener;
    }

    public void submitList(List<CartItem> cartItems) {
        items.clear();
        if (cartItems != null) {
            items.addAll(cartItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.title.setText(item.getProduct().getTitle());
        holder.price.setText(holder.itemView.getContext().getString(R.string.product_price_format, item.getProduct().getPrice()));
        holder.quantity.setText(holder.itemView.getContext().getString(R.string.cart_quantity_format, item.getQuantity()));
        Glide.with(holder.itemView.getContext()).load(item.getProduct().getImageUrl()).into(holder.image);
        holder.remove.setOnClickListener(v -> listener.onRemoveClicked(item.getProduct().getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView title;
        final TextView price;
        final TextView quantity;
        final Button remove;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cart_item_image);
            title = itemView.findViewById(R.id.cart_item_title);
            price = itemView.findViewById(R.id.cart_item_price);
            quantity = itemView.findViewById(R.id.cart_item_quantity);
            remove = itemView.findViewById(R.id.cart_item_remove);
        }
    }

    public interface CartItemListener {
        void onRemoveClicked(int productId);
    }
}
