package com.example.burgersystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.burgeronlinesystem.R;
import com.example.burgeronlinesystem.ViewOrderActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnOrder;
    private Button btnViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOrder = findViewById(R.id.btn_order);
        btnViewOrder = findViewById(R.id.btn_view_order);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.burgersystem.OrderActivity.Order.class);
                startActivity(intent);
            }
        });
        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
