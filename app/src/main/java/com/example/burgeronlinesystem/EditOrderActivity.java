package com.example.burgeronlinesystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditOrderActivity extends AppCompatActivity {

    private com.example.burgeronlinesystem.OrderActivity orderActivity;
    private EditText customerNameEditText;
    private EditText itemsOrderedEditText;
    private EditText totalAmountEditText;
    private EditText orderStatusEditText;
    private Button saveButton;

    @SuppressLint({"SetTextI18n", "MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edtorder);

        orderActivity = getIntent().getParcelableExtra("order");

        customerNameEditText = findViewById(R.id.edit_customer_name);
        itemsOrderedEditText = findViewById(R.id.items_ordered_edittext);
        orderStatusEditText = findViewById(R.id.order_status_textview);
        totalAmountEditText = findViewById(R.id.total_amount_edittext);
        saveButton = findViewById(R.id.save_btn);

        customerNameEditText.setText(com.example.burgeronlinesystem.OrderActivity.Order.getCustomerName());
        itemsOrderedEditText.setText((CharSequence) com.example.burgeronlinesystem.OrderActivity.Order.getItemsOrdered());
        totalAmountEditText.setText(Double.toString(com.example.burgeronlinesystem.OrderActivity.Order.getTotalAmount()));
        orderStatusEditText.setText(com.example.burgeronlinesystem.OrderActivity.Order.getOrderStatus());

        saveButton.setOnClickListener(new View.OnClickListener() {
            private com.example.burgeronlinesystem.OrderActivity OrderActivity;

            @Override
            public void onClick(View v) {
                String newCustomerName = customerNameEditText.getText().toString();
                String newItemsOrdered = itemsOrderedEditText.getText().toString();
                Double newTotalAmount = Double.parseDouble(totalAmountEditText.getText().toString());
                String newOrderStatus = orderStatusEditText.getText().toString();

                com.example.burgeronlinesystem.OrderActivity.Order.getCustomerName();
                com.example.burgeronlinesystem.OrderActivity.Order.getItemsOrdered();
                com.example.burgeronlinesystem.OrderActivity.Order.setTotalAmount(newTotalAmount);
                com.example.burgeronlinesystem.OrderActivity.Order.setOrderStatus(newOrderStatus);

        updateOrder(OrderActivity);

                Toast.makeText(EditOrderActivity.this, "Order Updated", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    private void updateOrder(com.example.burgeronlinesystem.OrderActivity orderActivity) {
    }

    public void setTotalAmountEditText(EditText totalAmountEditText) {
        this.totalAmountEditText = totalAmountEditText;
    }
}
