package com.example.furrytales.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.furrytales.R;
import com.example.furrytales.activity.ProductDetailsActivity;
import com.example.furrytales.api.API;
import com.example.furrytales.entity.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductListAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_product, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textTitle.setText(productList.get(position).getTitle());
        holder.textPrice.setText("Mrp - " + productList.get(position).getMrp());

        Glide.with(context)
                .load(API.BASE_URL+"/"+productList.get(position).getImage())
                .into(holder.imageProduct);
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProduct;
        TextView textTitle, textPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("product",productList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}