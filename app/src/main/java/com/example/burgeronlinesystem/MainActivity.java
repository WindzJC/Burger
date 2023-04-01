package com.example.burgeronlinesystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.burgeronlinesystem.R;
import com.example.burgeronlinesystem.ViewOrderActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnOrder;
    private Button edtorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOrder = findViewById(R.id.order);
        edtorder = findViewById(R.id.edtorder);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.burgeronlinesystem.OrderActivity.Order.class);
                startActivity(intent);
            }
        });
        edtorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewOrderActivity.class);
                startActivity(intent);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, SplashScreen.class);

                startActivity(i);

                finish();
            }
        }, 2000);

    }
}

