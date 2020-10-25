package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int quantity = 1;
    private boolean withWhippedCream = false;
    private boolean withChocolate = false;
    private String customerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.displayQuantity();
    }

    public void onOrderButtonClick(View view) {
        final CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_checkbox);
        final CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        final EditText customerNameField = findViewById(R.id.customer_name);
        this.customerName = customerNameField.getText().toString();
        this.withWhippedCream = whippedCreamCheckBox.isChecked();
        this.withChocolate = chocolateCheckBox.isChecked();
        final String orderSummary = createOrderSummary();

        final Intent intent = new Intent(Intent.ACTION_SEND);
        final String [] addresses = { "test@hitmail.com" };

        intent.setType("*/*");
        //intent.setData(Uri.parse("mailto:")); // only open mail apps

        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String createOrderSummary() {
        final int price = calculatePrice();
        final String formattedPrice = NumberFormat.getCurrencyInstance().format(price);
        return "Name: " + this.customerName
                + "\nWith Whipped Cream? - " + (this.withWhippedCream ? "Yes" : "No")
                + "\nWith Chocolate? - " + (this.withChocolate ? "Yes" : "No")
                + "\nQuantity: " + this.quantity
                + "\nTotal: " + formattedPrice
                + "\nThank you!";
    }

    private int calculatePrice() {
        final int basePrice = 5 * this.quantity;
        final int toppingsPrice = this.quantity * ((this.withChocolate ? 1 : 0) + (this.withWhippedCream ? 2 : 0));

        return basePrice + toppingsPrice;
    }

    public void onIncrementButtonClick(View view) {
        if (this.quantity == 100){
            Toast.makeText(this, "You have reached the maximum limit", Toast.LENGTH_SHORT).show();
            return;
        }

        this.quantity++;
        displayQuantity();
    }

    public void onDecrementButtonClick(View view) {
        if (this.quantity == 1){
            Toast.makeText(this, "You have reached the minimum limit", Toast.LENGTH_SHORT).show();
            return;
        }

        this.quantity--;
        displayQuantity();
    }

    private void displayQuantity() {
        final TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + this.quantity);
        Log.d(this.getLocalClassName(), "Quantity is: " + this.quantity);
    }
}