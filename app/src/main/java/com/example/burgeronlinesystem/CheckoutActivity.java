package com.example.burgeronlinesystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView itemsTextView;
    private TextView totalAmountTextView;
    private Button checkoutButton;

    private String itemsOrdered;
    private double totalAmount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        itemsOrdered = getIntent().getStringExtra("itemsOrdered");
        totalAmount = getIntent().getDoubleExtra("totalAmount", 0.0);

        itemsTextView = findViewById(R.id.items_ordered_edittext);
        totalAmountTextView = findViewById(R.id.total_amount_textview);
        checkoutButton = findViewById(R.id.checkout_button);

        itemsTextView.setText(itemsOrdered);
        totalAmountTextView.setText(String.format("$%.2f", totalAmount));

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CheckoutActivity.this, "Thank you for your order! Your " + " payment of $" + totalAmount + " has been received.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CheckoutActivity.this, com.example.burgeronlinesystem.OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
