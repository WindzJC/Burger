package com.example.burgeronlinesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.burgeronlinesystem.CheckoutActivity;

public class OrderActivity extends AppCompatActivity {
    ImageButton railwaycl, spicyaloo, greekf, spinachncorn;
    String burgname;
    String railwaycutlet, spicyaloocrunch,greekfalafel,spinachcorn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        railwaycl = findViewById(R.id.railwaycutlet);
        spicyaloo = findViewById(R.id.spicyaloo);
        greekf = findViewById(R.id.greekfalafel);
        spinachncorn = findViewById(R.id.burger4);

        railwaycutlet = "buger 1";
        spicyaloocrunch = "buger 2";
        greekfalafel = "buger 3";
        spinachcorn = "buger 4";

        railwaycl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burgname = railwaycutlet;
                Intent intent = new Intent(OrderActivity.this, CheckoutActivity.class);
                intent.putExtra("burger", burgname);
                intent.putExtra("value", 1);
                startActivity(intent);
            }
        });
        spicyaloo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burgname = spicyaloocrunch;
                Intent intent = new Intent(OrderActivity.this, CheckoutActivity.class);
                intent.putExtra("burger", burgname);
                intent.putExtra("value", 2);
                startActivity(intent);
            }
        });
        greekf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burgname = greekfalafel;
                Intent intent = new Intent(OrderActivity.this, CheckoutActivity.class);
                intent.putExtra("burger", burgname);
                intent.putExtra("value", 3);
                startActivity(intent);
            }
        });
        spinachncorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                burgname = spinachcorn;
                Intent intent = new Intent(OrderActivity.this, CheckoutActivity.class);
                intent.putExtra("burger", burgname);
                intent.putExtra("value", 4);
                startActivity(intent);
            }
        });
    }
}