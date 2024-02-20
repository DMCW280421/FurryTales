package com.example.furrytales.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.furrytales.R;
import com.example.furrytales.api.API;
import com.example.furrytales.api.RetrofitClient;
import com.example.furrytales.entity.Order;
import com.google.gson.JsonObject;

import fragments.CartFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utility.FurryTalesConstants;

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "CartFragment";

    Spinner spinnerCountries;
    EditText editTextAddress;
    TextView textViewEnteredAddress;
    CardView cardViewAddress;
    Button btnNext;
//    private CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        updateOrder();

        spinnerCountries = findViewById(R.id.spinner_countries);
        editTextAddress = findViewById(R.id.editTextAddress);
        cardViewAddress = findViewById(R.id.cardViewAddress);
        textViewEnteredAddress = findViewById(R.id.textViewEnteredAddress);
        btnNext = findViewById(R.id.btnNext);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerCountries.setAdapter(adapter);
        editTextAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateEnteredAddress(editable.toString());

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCountry = spinnerCountries.getSelectedItem().toString();
                String address = editTextAddress.getText().toString();
                Intent intent = new Intent(OrderActivity.this, CheckoutActivity.class);
                intent.putExtra("selectedCountry", selectedCountry);
                intent.putExtra("address", address);
                startActivity(intent);
            }
        });
    }
    private void updateEnteredAddress(String address) {
        if (!TextUtils.isEmpty(address)) {
            cardViewAddress.setVisibility(View.VISIBLE);
            textViewEnteredAddress.setText(address);
        } else {
            cardViewAddress.setVisibility(View.GONE);
        }
    }


        public void updateOrder() {
            // Define TAG constant
            String token = getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
                    .getString(FurryTalesConstants.AUTH_TOKEN, "");

            // Get token from SharedPreferences
            RetrofitClient.getInstance().getApi().updateOrder(token).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        // The request was successful, handle the response here
                        Log.d(TAG, "Order update successful");
                    } else {
                        // The request was not successful, handle the error here
                        Log.e(TAG, "Failed to update order. Response code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e(TAG, "Failed to create order. Error: " + t.getMessage());
                }
            });
        }


    }
