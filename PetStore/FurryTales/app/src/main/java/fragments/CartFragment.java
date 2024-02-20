package fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furrytales.R;
import com.example.furrytales.activity.CheckoutActivity;
import com.example.furrytales.activity.OrderActivity;
import com.example.furrytales.adapter.CartAdapter;
import com.example.furrytales.api.RetrofitClient;
import com.example.furrytales.entity.Cart;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utility.CartItemListener;
import utility.FurryTalesConstants;


public class CartFragment extends Fragment implements CartItemListener {

    private static final String TAG = "CartFragment";

    RecyclerView recyclerView;

    TextView totalAmountView;
    Button btnCheckout;

    List<Cart> cartList;

    CartAdapter cartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnCheckout = view.findViewById(R.id.btnCheckout); // Initialize the button
        totalAmountView = view.findViewById(R.id.totalAmountView);
        return view;
//        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(getContext(), cartList, this);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        super.onViewCreated(view, savedInstanceState);
        getCart();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("totalAmount", calculateTotalAmount());
                startActivity(intent);
            }
        });

    }

    public void getCart() {
        String token = getContext().getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .getString(FurryTalesConstants.AUTH_TOKEN, "");
        RetrofitClient.getInstance().getApi().getCart(token).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "API call onResponse");
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject responseBody = response.body();
                    Log.d(TAG, "Response Body: " + responseBody.toString());
                    if (responseBody.has("status") && responseBody.get("status").getAsString().equals("success")) {
                        JsonArray array = responseBody.getAsJsonArray("data");
                        if (array != null && !array.isJsonNull()) {
                            Log.d(TAG, "Cart data received. Parsing...");
                            cartList.clear();
                            for (JsonElement element : array) {
                                JsonObject object = element.getAsJsonObject();
                                if (object != null && !object.isJsonNull()) {
                                    Cart cart = new Cart();
                                    cart.setCartID(object.get("cartID").getAsInt());
                                    cart.setProductID(object.get("productID").getAsInt());
                                    cart.setQuantity(object.get("quantity").getAsInt());
                                    cart.setMrp(object.get("mrp").getAsInt());
                                    cart.setDiscountedPrice(object.get("discountedPrice").getAsInt());
                                    cart.setTotal(object.get("total").getAsInt());

                                    // Set additional fields
                                    if (object.has("title") && !object.get("title").isJsonNull()) {
                                        cart.setTitle(object.get("title").getAsString());
                                    }
                                    if (object.has("category") && !object.get("category").isJsonNull()) {
                                        cart.setCategory(object.get("category").getAsString());
                                    }
                                    if (object.has("company") && !object.get("company").isJsonNull()) {
                                        cart.setCompany(object.get("company").getAsString());
                                    }
                                    if (object.has("image") && !object.get("image").isJsonNull()) {
                                        cart.setImage(object.get("image").getAsString());
                                    }

                                    cartList.add(cart);
                                }
                            }
                            cartAdapter.notifyDataSetChanged();
                            updateTotalAmount();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "API call onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Failed to fetch cart data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double calculateTotalAmount() {
        double totalAmount = 0;
        for (Cart cart : cartList) {
            totalAmount += cart.getTotal() * cart.getMrp();
        }
        return totalAmount;
    }

    private void updateTotalAmount() {
        Log.d(TAG, "Updating total amount...");
        double totalAmount = calculateTotalAmount();
        Log.d(TAG, "Total amount calculated: " + totalAmount);
        totalAmountView.setText("Total: \u20B9" + totalAmount);
    }
    private void updateCartItemQuantity(int cartID, int quantityChange) {
        if (cartID >= 0 && cartID < cartList.size()) { // Check if cartID is a valid index
            String token = getContext().getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                    .getString(FurryTalesConstants.AUTH_TOKEN, "");
            Log.d(TAG, "Updating cart item quantity. Cart ID: " + cartID + ", Quantity Change: " + quantityChange);

            // Calculate the new quantity by adding the quantity change
            int newQuantity = cartList.get(cartID).getQuantity() + quantityChange;

            // If the new quantity is less than 1, delete the cart item
            if (newQuantity < 1) {
                deleteCartItem(cartID);
                return;
            }

            Cart updatedCart = new Cart();
            updatedCart.setCartID(cartID);
            updatedCart.setQuantity(newQuantity);
            RetrofitClient.getInstance().getApi().updateCartItemQuantity(token, updatedCart).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JsonObject responseBody = response.body();
                        if (responseBody.has("status") && responseBody.get("status").getAsString().equals("success")) {
                            // Log success message
                            Log.d(TAG, "Cart item quantity updated successfully.");
                            // Refresh cart data
                            refreshCart();
                        } else {
                            // Log failure message
                            Log.d(TAG, "Failed to update cart item quantity. Server response: " + responseBody.toString());
                            // Show error message to user
                            Toast.makeText(getContext(), "Failed to update cart item quantity", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle unsuccessful response or errors from the server
                        // Log error message
                        Log.e(TAG, "Failed to update cart item quantity. Response code: " + response.code());
                        // Show error message to user
                        Toast.makeText(getContext(), "Failed to update cart item quantity", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    // Handle failure to make the API call
                    Log.e(TAG, "API call onFailure: " + t.getMessage());
                    Toast.makeText(getContext(), "Failed to update cart item quantity", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.e(TAG, "Invalid cartID: " + cartID);
            // Handle the invalid cartID scenario (e.g., show error message to the user)
        }
    }

    private void deleteCartItem(int cartID) {
        String token = getContext().getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                .getString(FurryTalesConstants.AUTH_TOKEN, "");
        Log.d(TAG, "Deleting cart item. Cart ID: " + cartID);

        RetrofitClient.getInstance().getApi().deleteCartItem(token, cartID).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject responseBody = response.body();
                    if (responseBody.has("status") && responseBody.get("status").getAsString().equals("success")) {
                        refreshCart();
                    } else {
                        Toast.makeText(getContext(), "Failed to delete cart item", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Handle unsuccessful response or errors from the server
                    Toast.makeText(getContext(), "Failed to delete cart item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Handle failure to make the API call
                Log.e(TAG, "API call onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Failed to delete cart item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onIncreaseClicked(int cartID) {
        updateCartItemQuantity(cartID, 1);
        Log.d(TAG, "Increasing quantity for cart ID: " + cartID);

    }

    @Override
    public void onDecreaseClicked(int cartID) {
        updateCartItemQuantity(cartID, -1);
        Log.d(TAG, "Decreasing quantity for cart ID: " + cartID);

    }

    @Override
    public void onDeleteClicked(int cartID) {
        // Implement logic to delete the item from the cart
        Log.d(TAG, "Deleting cart item with ID: " + cartID);

    }

    private void refreshCart() {
        cartList.clear();
        getCart();
        cartAdapter.notifyDataSetChanged();

    }


    }
