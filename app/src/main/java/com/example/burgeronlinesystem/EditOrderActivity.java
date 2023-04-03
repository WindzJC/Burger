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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class EditOrderActivity extends AppCompatActivity {

    private static Button btnQuery, btnCancel;
    private static EditText name, burger, qty;
    private static TextView tv_civ;
    private static String cItemcode = "";
    private static JSONParser jParser = new JSONParser();
    private static String urlHost = "http://192.168.0.105/burgerphp/UpdateQty.php";
    private static String TAG_MESSAGE = "message", TAG_SUCCESS = "success";
    private static String online_dataset = "";

    public static final String FULLNAME = "FULLNAME";
    public static final String BURGER = "BURGER";
    public static final String QTY = "QTY";
    public static String ID = "ID";
    private String nme, burg, quanti, aydi;
    public static String Name = "";
    public static String Burger = "";
    public static String Qty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edtorder);

        name = (EditText) findViewById(R.id.edit_customer_name);
        burger = (EditText) findViewById(R.id.items_ordered_edittext);
        qty = (EditText) findViewById(R.id.qty_edit);
        btnQuery = (Button) findViewById(R.id.save_btn);
        btnCancel = (Button) findViewById(R.id.cancel_btn);

        Intent i = getIntent();
        nme = i.getStringExtra(FULLNAME);
        burg = i.getStringExtra(BURGER);
        quanti = i.getStringExtra(QTY);
        aydi = i.getStringExtra(ID);

        name.setText(nme);
        burger.setText(burg);
        qty.setText(quanti);

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                Burger = burger.getText().toString();
                Qty = qty.getText().toString();
                new uploadDataToURL().execute();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditOrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private class uploadDataToURL extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        String gens, civil;
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(EditOrderActivity.this);

        public uploadDataToURL() {}
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
                cPostSQL = aydi;
                cv.put("OrderNum", cPostSQL);

                cPostSQL = " '" + Name + "' ";
                cv.put("Name", cPostSQL);

                cPostSQL = " '" + Burger + "' ";
                cv.put("burger", cPostSQL);

                cPostSQL =  " '" + Qty + "' ";
                cv.put("qty", cPostSQL);


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
            AlertDialog.Builder alert = new AlertDialog.Builder(EditOrderActivity.this);
            if (s != null) {
                if (isEmpty.equals("") && !s.equals("HTTPSERVER_ERROR")) {}
                Toast.makeText(EditOrderActivity.this, s ,Toast.LENGTH_SHORT).show();
            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet Connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }
}