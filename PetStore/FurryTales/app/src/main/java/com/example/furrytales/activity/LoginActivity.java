package com.example.furrytales.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.furrytales.R;
import com.example.furrytales.api.RetrofitClient;
import com.example.furrytales.entity.Customer;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utility.FurryTalesConstants;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    CheckBox checkboxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        TextInputLayout usernameLayout = findViewById(R.id.username);
        TextInputLayout passwordLayout = findViewById(R.id.password);
        username = usernameLayout.getEditText(); // Find the EditText inside TextInputLayout
        password = passwordLayout.getEditText(); // Find the EditText inside TextInputLayout
        checkboxRememberMe = findViewById(R.id.checkboxRememberMe);
    }
    public void login(View view) {
        Customer customer = new Customer();
        customer.setEmail(username.getText().toString());
        customer.setPassword(password.getText().toString());
        Log.d("Login","user details: "+customer.toString());

        if (checkboxRememberMe.isChecked()){
            getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME,MODE_PRIVATE)
                    .edit()
                    .putBoolean(FurryTalesConstants.LOGIN_STATUS,true)
                    .apply();
        }

        RetrofitClient.getInstance().getApi().loginCustomer(customer).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject responseBody = response.body();
                    Log.d("Login", "Response Body: " + responseBody.toString());

                    if (responseBody.has("status") && responseBody.get("status").getAsString().equals("success") &&
                            responseBody.has("data")) {
                        JsonObject data = responseBody.getAsJsonObject("data");
                        if (data.has("token") && data.has("id") && data.has("name")) {
                            int id = data.get("id").getAsInt();
                            String token = data.get("token").getAsString();

                            // Save the user ID to SharedPreferences
                            getSharedPreferences(FurryTalesConstants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE)
                                    .edit()
                                    .putInt(FurryTalesConstants.CUSTOMER_ID, id)
                                    .putString(FurryTalesConstants.AUTH_TOKEN, token)
                                    .apply();

                            // Make another API call with the obtained token
                            //getAllProducts(authToken);

                            // Start the DashboardActivity
                            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                            finish();
                        }
                    }
                }
            }



            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong at the Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void register(View view) {
        startActivity(new Intent(this, RegisterationActivity.class));
    }
    }
