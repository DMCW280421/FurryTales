package com.example.furrytales.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.furrytales.R;
import com.example.furrytales.api.RetrofitClient;
import com.example.furrytales.entity.Customer;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterationActivity extends AppCompatActivity {

    EditText editName,editEmail,editMobile,editAddress,editPassword,editConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        // Find the TextInputLayouts
        TextInputLayout NameLayout = findViewById(R.id.editName);
        TextInputLayout emailLayout = findViewById(R.id.editEmail);
        TextInputLayout passwordLayout = findViewById(R.id.editPassword);
        TextInputLayout confirmPasswordLayout = findViewById(R.id.editConfirmPassword);
        TextInputLayout mobileLayout = findViewById(R.id.editMobile);
        TextInputLayout addressLayout = findViewById(R.id.editAddress);

        // Get the EditTexts from the TextInputLayouts

        editName = NameLayout.getEditText();
        editEmail = emailLayout.getEditText();
        editPassword = passwordLayout.getEditText();
        editConfirmPassword = confirmPasswordLayout.getEditText();
        editMobile = mobileLayout.getEditText();
        editAddress = addressLayout.getEditText();
    }
    public void register(View view) {
        Customer customer = new Customer();
        customer.setName(editName.getText().toString());
        Log.e("this" ,"Customer name: "+customer.getName());
        customer.setEmail(editEmail.getText().toString());
        customer.setPassword(editPassword.getText().toString());
        customer.setPhone(editMobile.getText().toString());
        customer.setAddress(editAddress.getText().toString());

        Log.d("Registration","user details: "+customer.toString());
        if (customer.getEmail().equals("") || customer.getPassword().equals(""))
            Toast.makeText(this, "email or password cannot be empty", Toast.LENGTH_SHORT).show();
        else {
            RetrofitClient.getInstance().getApi().registerCustomer(customer).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JsonObject body = response.body();
                        JsonElement statusElement = body.get("status");
                        if (statusElement != null && statusElement.getAsString().equals("success")) {
                            finish();
                        } else {
                            if (body.has("error") && body.getAsJsonObject("error").has("errno")) {
                                int errno = body.getAsJsonObject("error").get("errno").getAsInt();
                                if (errno == 1062)
                                    Toast.makeText(RegisterationActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                                else if (errno == 1406)
                                    Toast.makeText(RegisterationActivity.this, "Mobile number is incorrect", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(RegisterationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(RegisterationActivity.this, "Failed to get response", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e("Registration","onFailure: "+t.getMessage());
                    Toast.makeText(RegisterationActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    public void cancel(View view){
        finish();
    }
}