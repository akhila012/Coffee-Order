package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       String priceMessage="NAME=";
        int price,eachPrice=5;
        CheckBox check1 = (CheckBox) findViewById(R.id.checkBox_whippedcream);
        EditText text=(EditText) findViewById(R.id.editText);
        CheckBox check2= (CheckBox) findViewById(R.id.checkBox_iceCream);
                priceMessage+=text.getText().toString();
        if(check1.isChecked()&&check2.isChecked())
        {eachPrice+=2;
        priceMessage +="\n whipped cream:yes"+"\n IceCream:yes";}
        else if(check1.isChecked())
        {   eachPrice++;
            priceMessage +="\n whipped cream:yes";
        }
        else
        {   eachPrice++;
            priceMessage +="\n IceCream:yes";
        }
        price=quantity*eachPrice;
        displayMessage(priceMessage, price);

    }
    /*  This method is called when "+" button is clicked*/
    public void increment(View view){
        if(quantity>99)
        { Toast.makeText(this,"cannot be more than 100",Toast.LENGTH_SHORT).show();
            return;}
        quantity++;
        display(quantity);

    }
    /* This method is called when "-" button is clicked*/
    public void decrement(View view){
        if(quantity<1){
            Toast.makeText(this,"cannot be less than 1",Toast.LENGTH_SHORT).show();
            return;}
        quantity--;
        display(quantity);
            }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message ,int price) {
        EditText t=(EditText) findViewById(R.id.editText1);
        String str;
        str=t.getText().toString();
        message+="\n"+" quantity:"+quantity;
        message+="\n "+"TOTAL = Rs."+price+"\n"+" thank you";
        ConTest conTest=new ConTest(this);
        conTest.execute(str,message);
    }
}