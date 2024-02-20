package com.example.furrytales.activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.furrytales.R;
import com.example.furrytales.api.API;
import com.example.furrytales.api.RetrofitClient;
import com.example.furrytales.entity.Product;
import com.google.gson.JsonObject;

import java.util.Locale;

import fragments.CartFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.FurryTalesConstants;

public class ProductDetailsActivity extends AppCompatActivity {
    TextView textTitle, textDescription, textMrp, textDiscount,textCategory,textCompany;
    ImageView imageProductDetail;
    Button btnAddtoCart;

    Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize views
        textTitle = findViewById(R.id.textTitle);
        textDescription = findViewById(R.id.textDescription);
        textMrp = findViewById(R.id.textMrp);
        textDiscount = findViewById(R.id.textDiscount);
        imageProductDetail = findViewById(R.id.imageProductDetail);
        textCategory = findViewById(R.id.textCategory);
        textCompany = findViewById(R.id.textCompany);
        btnAddtoCart = findViewById(R.id.btnAddtoCart);

        // Get product object from intent
        product = (Product) getIntent().getSerializableExtra("product");

        Log.d("ProductDetailsActivity", "Received product: " + product);

        // Check if product is not null before accessing its properties
        if (product != null) {
            // Set text views with product data
            textTitle.setText(product.getTitle());
            textDescription.setText(product.getDescription());
            textCompany.setText(product.getCompany());
            textCategory.setText(product.getCategory());

            // Convert float values to strings when setting text
            textMrp.setText("Mrp : " + String.format(Locale.getDefault(), "%.2f", product.getMrp()));
            textDiscount.setText("Discount : " + String.format(Locale.getDefault(), "%.2f", product.getDiscount()) + "%");

            // Load image with Glide only if imageProduct is not null
            if (imageProductDetail != null) {
                Glide.with(this)
                        .load(API.BASE_URL + "/" + product.getImage())
                        .placeholder(R.drawable.furrylogo) // Placeholder image resource
                        .error(R.drawable.product_list) // Error image resource
                        .into(imageProductDetail);
            } else {
                Log.e("ProductDetailsActivity", "imageProduct is null");
            }
            btnAddtoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addToCart(product);
                }
            });
        }
    }

    public void addToCart(Product product) {
        String token = getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .getString(FurryTalesConstants.AUTH_TOKEN, "");


        Log.d("addToCart", "Token : " +token);
        API api = RetrofitClient.getInstance().getApi();
        if (token.isEmpty()) {
            Toast.makeText(this, "Token not available. Please login.", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("addToCart", "Product details: " + product.toString());
        api.addToCart(token, product).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.d("addToCart Response", String.valueOf(response));
                    Log.d("addToCart", "Product added to cart successfully");
                    Toast.makeText(ProductDetailsActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
               refreshCartData();
                } else {
                    Log.d("addToCart", "Failed to add product to cart. Response code: " + response.code());
                    Toast.makeText(ProductDetailsActivity.this, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Handle failure
                Log.e("addToCart", "Failed to add product to cart. Error: " + t.getMessage());
                Toast.makeText(ProductDetailsActivity.this, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void refreshCartData() {
        // If your CartFragment instance is available, you can call its method to refresh the cart
        // For example:
        CartFragment cartFragment = (CartFragment) getSupportFragmentManager().findFragmentById(R.id.cart_fragment);
        if (cartFragment != null) {
            cartFragment.getCart();
        }
    }
}

//        // Retrieve token from SharedPreferences
//        String token = getSharedPreferences(FurryTalesConstants.AUTH_TOKEN, MODE_PRIVATE).getString("token", "");
//
//        // Check if the token is available
//        if (token.isEmpty()) {
//            Toast.makeText(this, "Token not available. Please login.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Obtain API instance from RetrofitClient
//        API api = RetrofitClient.getInstance().getApi();
//
//        // Make the API call to add the product to the cart
//        Call<JsonObject> addToCartCall = api.addToCart(token, product);
//        addToCartCall.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                if (response.isSuccessful()) {
//                    JsonObject responseBody = response.body();
//                    if (responseBody != null && responseBody.has("status")) {
//                        String status = responseBody.get("status").getAsString();
//                        if ("success".equals(status)) {
//                            Toast.makeText(ProductDetailsActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(ProductDetailsActivity.this, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                } else {
//                    Toast.makeText(ProductDetailsActivity.this, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Toast.makeText(ProductDetailsActivity.this, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
