package com.example.burgeronlinesystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewOrderActivity extends AppCompatActivity {

    private TextView orderDetailsTextView;
    private TextView orderStatusTextView;
    private Button cancelButton;
    private com.example.burgersystem.OrderActivity currentOrderActivity;
    private EditText newTotalAmount,newItemsOrdered,customerName;

    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        currentOrderActivity = getIntent().getParcelableExtra("order");

        orderDetailsTextView = findViewById(R.id.order_status_textview);
        orderStatusTextView = findViewById(R.id.order_status_textview);
        cancelButton = findViewById(R.id.cancel_button);

        String orderDetailsText = "Order ID: " + com.example.burgersystem.OrderActivity.Order.getId() + "\n"
                + "Customer Name: " + com.example.burgersystem.OrderActivity.Order.getCustomerName() + "\n"
                + "Items Ordered: " + com.example.burgersystem.OrderActivity.Order.getItemsOrdered() + "\n"
                + "Total Amount: " + com.example.burgersystem.OrderActivity.Order.getTotalAmount() + "\n";

        orderDetailsTextView.setText(orderDetailsText);

        String orderStatusText = "Order Status: " + com.example.burgersystem.OrderActivity.Order.getOrderStatus();
        orderStatusTextView.setText(orderStatusText);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.burgersystem.OrderActivity.Order.setOrderStatus("Cancelled");

                updateOrder(currentOrderActivity);

                Toast.makeText(ViewOrderActivity.this, "Order Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void updateOrder(com.example.burgersystem.OrderActivity currentOrderActivity) {
    }
}
