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
        final TextView priceTextView = findViewById(R.id.price_text_view);
        final String totalPrice = NumberFormat.getCurrencyInstance().format(5 * this.quantity);
        final String orderMessage = "Total: " + totalPrice + "\nThank you!";

        if (this.quantity > 0) {
            priceTextView.setText(orderMessage);
        }
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