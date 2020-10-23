package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.displayQuantity();
    }

    public void onOrderButtonClick(View view) {
        final TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        final String orderSummary = createOrderSummary();

        if (this.quantity > 0) {
            orderSummaryTextView.setText(orderSummary);
        }
    }

    private String createOrderSummary() {
        final int price = calculatePrice();
        final String formattedPrice = NumberFormat.getCurrencyInstance().format(price);
        return "Name: DD\nQuantity: " + this.quantity +  "\nTotal: " + formattedPrice + "\nThank you!";
    }

    private int calculatePrice() {
        return 5 * this.quantity;
    }

    public void onIncrementButtonClick(View view) {
        this.quantity++;
        displayQuantity();
    }

    public void onDecrementButtonClick(View view) {
        if (this.quantity > 0) this.quantity--;
        displayQuantity();
    }

    private void displayQuantity() {
        final TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + this.quantity);
    }
}