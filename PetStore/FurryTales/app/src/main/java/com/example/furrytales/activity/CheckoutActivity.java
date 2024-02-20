package com.example.furrytales.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.furrytales.R;

import utility.NotificationUtils;

public class CheckoutActivity extends AppCompatActivity {
    TextView textTotalAmount;
    TextView textAddress;
    Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        textTotalAmount = findViewById(R.id.textTotalAmount);
        textAddress = findViewById(R.id.textAddress);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        double totalAmount = getIntent().getDoubleExtra("totalAmount",0.0);
        String address = getIntent().getStringExtra("address");

        textTotalAmount.setText("Total Amount: $" + totalAmount);
        textAddress.setText("Delivery Address: " + address);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(CheckoutActivity.this,"Order Placed", "Your order has been placed successfully!");
            }
        });
    }
    private void sendNotification(CheckoutActivity checkoutActivity, String title, String content) {
        NotificationUtils.showNotification(this, title, content);
    }

    }
