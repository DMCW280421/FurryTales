package com.example.furrytales.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.furrytales.R;
import com.example.furrytales.activity.CartDetailsActivity;
import com.example.furrytales.activity.ProductDetailsActivity;
import com.example.furrytales.api.API;
import com.example.furrytales.api.RetrofitClient;
import com.example.furrytales.entity.Cart;
import com.example.furrytales.entity.Product;

import java.util.List;

import utility.CartItemListener;
import utility.FurryTalesConstants;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private Context context;
    private List<Cart> cartList;
    private CartItemListener cartItemListener;
    private static final String TAG = "CartFragment";

    public CartAdapter(Context context, List<Cart> cartList, CartItemListener cartItemListener) {
        this.context = context;
        this.cartList = cartList;
        this.cartItemListener = cartItemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cart, null);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.textTitle.setText(cart.getTitle());
        holder.textPrice.setText("Mrp - " + cart.getMrp());
        holder.textQuantity.setText("Quantity : " + cart.getQuantity());

        Glide.with(context)
                .load(API.BASE_URL + "/" + cart.getImage())
                .into(holder.imageProduct);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cartID = cart.getCartID();
                cartItemListener.onDeleteClicked(cartID);
            }
        });
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cartID = cart.getCartID();
                cartItemListener.onIncreaseClicked(cartID);
            }
        });

        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cartID = cart.getCartID();
                Log.d(TAG, "Increase button clicked for cartID : " + cartID);
                cartItemListener.onDecreaseClicked(cartID);
                }

        });
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView textTitle, textPrice, textQuantity;
        Button btnIncrease, btnDecrease, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}