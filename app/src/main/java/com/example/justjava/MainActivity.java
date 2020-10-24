package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int quantity;
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
        final TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        final CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_checkbox);
        final CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        final EditText customerNameField = findViewById(R.id.customer_name);
        this.customerName = customerNameField.getText().toString();
        this.withWhippedCream = whippedCreamCheckBox.isChecked();
        this.withChocolate = chocolateCheckBox.isChecked();
        final String orderSummary = createOrderSummary();

        if (this.quantity > 0) {
            orderSummaryTextView.setText(orderSummary);
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
        this.quantity++;
        displayQuantity();
    }

    public void onDecrementButtonClick(View view) {
        if (this.quantity > 1) this.quantity--;
        displayQuantity();
    }

    private void displayQuantity() {
        final TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + this.quantity);
        Log.d(this.getLocalClassName(), "Quantity is: " + this.quantity);
    }
}