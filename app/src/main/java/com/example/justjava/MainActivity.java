package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private String customerName = "";

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
        final String yesString = getString(R.string.yes);
        final String noString = getString(R.string.no);
        String orderSummary = getString(R.string.order_summary_name, this.customerName) + "\n";

        orderSummary += getString(R.string.order_summary_whipped_cream) + (this.withWhippedCream ? yesString : noString);
        orderSummary += getString(R.string.order_summary_choco) + (this.withChocolate ? yesString : noString);
        orderSummary += getString(R.string.order_summary_quantity) + this.quantity;
        orderSummary += getString(R.string.order_summary_total) + formattedPrice;
        orderSummary += getString(R.string.thank_you);
        return orderSummary;
    }

    private int calculatePrice() {
        final int basePrice = 5 * this.quantity;
        final int toppingsPrice = this.quantity * ((this.withChocolate ? 1 : 0) + (this.withWhippedCream ? 2 : 0));

        return basePrice + toppingsPrice;
    }

    public void onIncrementButtonClick(View view) {
        if (this.quantity == 100){
            final String maxLimitMessage = getString(R.string.max_limit_msg);
            Toast.makeText(this, maxLimitMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        this.quantity++;
        displayQuantity();
    }

    public void onDecrementButtonClick(View view) {
        if (this.quantity == 1) {
            final String minLimitMessage = getString(R.string.max_limit_msg);
            Toast.makeText(this, minLimitMessage, Toast.LENGTH_SHORT).show();
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