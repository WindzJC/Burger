package com.example.burgeronlinesystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditOrderActivity extends AppCompatActivity {

    private com.example.burgersystem.OrderActivity orderActivity;
    private EditText customerNameEditText;
    private EditText itemsOrderedEditText;
    private EditText totalAmountEditText;
    private EditText orderStatusEditText;
    private Button saveButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorder);

        orderActivity = getIntent().getParcelableExtra("order");

        customerNameEditText = findViewById(R.id.edit_customer_name);
        itemsOrderedEditText = findViewById(R.id.items_ordered_edittext);
        orderStatusEditText = findViewById(R.id.order_status_edittext);
        totalAmountEditText = findViewById(R.id.total_amount_edittext);
        saveButton = findViewById(R.id.save_button);

        customerNameEditText.setText(com.example.burgersystem.OrderActivity.Order.getCustomerName());
        itemsOrderedEditText.setText((CharSequence) com.example.burgersystem.OrderActivity.Order.getItemsOrdered());
        totalAmountEditText.setText(Double.toString(com.example.burgersystem.OrderActivity.Order.getTotalAmount()));
        orderStatusEditText.setText(com.example.burgersystem.OrderActivity.Order.getOrderStatus());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCustomerName = customerNameEditText.getText().toString();
                String newItemsOrdered = itemsOrderedEditText.getText().toString();
                Double newTotalAmount = Double.parseDouble(totalAmountEditText.getText().toString());
                String newOrderStatus = orderStatusEditText.getText().toString();

                com.example.burgersystem.OrderActivity.Order.getCustomerName();
                com.example.burgersystem.OrderActivity.Order.getItemsOrdered();
                com.example.burgersystem.OrderActivity.Order.setTotalAmount(newTotalAmount);
                com.example.burgersystem.OrderActivity.Order.setOrderStatus(newOrderStatus);

        updateOrder(orderActivity);

                Toast.makeText(EditOrderActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    private void updateOrder(com.example.burgersystem.OrderActivity orderActivity) {
    }

    public void setTotalAmountEditText(EditText totalAmountEditText) {
        this.totalAmountEditText = totalAmountEditText;
    }
}
