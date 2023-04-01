package com.example.burgeronlinesystem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.burgeronlinesystem.CheckoutActivity;
import com.example.burgeronlinesystem.EditOrderActivity;
import com.example.burgeronlinesystem.R;
import com.example.burgeronlinesystem.ViewOrderActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private ArrayList<Order> orderItems;
    private ArrayAdapter<Order> adapter;
    private ListView listView;
    private Button checkoutBtn;

    public OrderActivity(int i, String veggie_burger, double v) {

    }
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderItems = getOrderItems();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, orderItems);
        listView = findViewById(R.id.items_textview);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        checkoutBtn = findViewById(R.id.checkout_button);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this, CheckoutActivity.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Open view order item activity for selected order item
                Intent intent = new Intent(OrderActivity.this, ViewOrderActivity.class);
                intent.putExtra("orderItem", orderItems.get(position));
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Show options to edit or delete selected order item
                showOptionsDialog(position);
                return true;
            }
        });
    }

    private ArrayList<Order> getOrderItems() {
        ArrayList<Order> orderItems = new ArrayList<>();
        orderItems.add(new Order(1, "Veggie Burger", 10.99));
        orderItems.add(new Order(2, "Veggie Wrap", 8.99));
        orderItems.add(new Order(3, "Veggie Salad", 7.99));
        // ...
        return orderItems;
    }

    private void showOptionsDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setItems(new CharSequence[]{"Edit Order Item", "Delete Order Item"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent editIntent = new Intent(OrderActivity.this, EditOrderActivity.class);
                        editIntent.putExtra("orderItem", orderItems.get(position));
                        startActivityForResult(editIntent, 1);
                        break;
                    case 1:
                        // Show confirmation dialog for deleting order item
                        showConfirmationDialog(position);
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void showConfirmationDialog(int position) {
    }

    public static class Order implements Serializable {

        private static int id;
        private static String customerName;
        private static ArrayList<String> itemsOrdered;
        private static double totalAmount;
        private static String orderStatus;

        public Order(int id, String veggie_burger, double v) {
        }

        public static int getId() {
            return id;
        }

        public static String getCustomerName() {
            return customerName;
        }

        public static ArrayList<String> getItemsOrdered() {
            return itemsOrdered;
        }

        public static double getTotalAmount() {
            return totalAmount;
        }


        public static String getOrderStatus() {
            return orderStatus;
        }

        public static void setTotalAmount(double newTotalAmount) {
            totalAmount = newTotalAmount;
        }


        public static void setOrderStatus(String orderStatus) {
            Order.orderStatus = orderStatus;
        }

        @Override
        public String toString() {
            return "Order #" + id + " - Total: $" + totalAmount;
        }
    }
}