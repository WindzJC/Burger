package com.example.burgeronlinesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class CheckoutActivity extends AppCompatActivity {

    private static JSONParser jParser = new JSONParser();
    private static String urlHost = "http://192.168.0.105/burgerphp/insert.php";
    private static String TAG_MESSAGE = "message" , TAG_SUCCESS = "success";
    private static String online_dataset = "";
    Button checkout;
    TextView  name;
    EditText qty;
    ImageView burgimg;
    String Name, burger, month, day, year, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Calendar date = Calendar.getInstance();
        name = findViewById(R.id.name);
        qty = findViewById(R.id.qty);
        checkout = findViewById(R.id.checkout_button);
        burgimg = findViewById(R.id.burgerImg);
        Intent intent = getIntent();
        burger = intent.getStringExtra("burger");
        int value = intent.getIntExtra("value", 0);


        switch (value) {
            case 1:
                burgimg.setImageResource(R.drawable.burger1);
                break;
            case 2:
                burgimg.setImageResource(R.drawable.burger2);
                break;
            case 3:
                burgimg.setImageResource(R.drawable.burger3);
                break;
            case 4:
                burgimg.setImageResource(R.drawable.burger4);
                break;
        }


        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                if ((date.get(Calendar.MONTH) + 1) < 10) {
                    month = "0" + String.valueOf(date.get(Calendar.MONTH) + 1);
                } else {
                    month = String.valueOf(date.get(Calendar.MONTH) + 1);
                }
                if ((date.get(Calendar.DAY_OF_MONTH)) < 10) {
                    day = "0" + String.valueOf(date.get(Calendar.DAY_OF_MONTH));
                } else {
                    day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
                }
                year = String.valueOf(date.get(Calendar.YEAR));
                quantity = qty.getText().toString();
                if (quantity.equals("")) {
                    quantity = "0";
                    qty.setText(quantity);
                }
                new uploadDatatoURL().execute();
            }
        });
    }


    private class uploadDatatoURL extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(CheckoutActivity.this);

        public uploadDatatoURL() { }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setMessage(cMessage);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            int nSuccess;
            try {
                ContentValues cv = new ContentValues();
                cPostSQL = " '" + Name + "' , '" + burger + "' , '" + quantity + "' , '" + day + "' , '" + month + "' , '" + year + "'  ";
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHost, "POST", cv);
                if(json != null) {
                    nSuccess = json.getInt(TAG_SUCCESS);
                    if(nSuccess == 1) {
                        online_dataset = json.getString(TAG_MESSAGE);
                        return online_dataset;
                    } else {
                        return json.getString(TAG_MESSAGE);
                    }
                } else {
                    return "HTTPSERVER_ERROR";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            String isEmpty = "";
            AlertDialog.Builder alert = new AlertDialog.Builder(CheckoutActivity.this);
            if (s != null) {
                if (isEmpty.equals("") && !s.equals("HTTPSERVER_ERROR")) { }
                Toast.makeText(CheckoutActivity.this, s, Toast.LENGTH_SHORT).show();
            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet Connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }
}
