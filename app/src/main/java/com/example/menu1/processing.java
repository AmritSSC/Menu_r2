package com.example.menu1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class processing extends AppCompatActivity {

    int[] text_viewID = {0, 1, 2, 3}; // List of names of created Views
    int[] edit_viewID = {0, 1, 2 ,3}; // List of names of created Views
    int submit_viewID = 0; // List of names of created Views
    int radioGrp_viewID = 0; // List of names of created Views
    int[] radioBtn_viewID = {0, 0, 0}; // List of names of created Views

    //set bundle variables:
    String[] itemName;
    String[] itemPrice;
    String[] itemQty;

    String subtotal;
    Double deliveryCharge = 0.00;
    Double orderTotal = 0.00;
    Double tax = 0.00;
    Double taxrate = 0.10;
    Double Total = 0.00;

    String[] cardData;
    TextView subtotalView;
    TextView orderDataView;
    TextView taxView;
    TextView totalView;

    EditText deliveryMethod;
    EditText cardView;
    EditText cardDateView;
    EditText cardVerifyView;

    Button submitBtnView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//////////////  Grab data from prevvious activity
        // data from previous page get bundle
        Bundle b = this.getIntent().getExtras();

//        String[][] orderList =b.getStringArrayList("orderList");
        itemName = b.getStringArray("itemName");
        itemPrice = b.getStringArray("itemPrice");
        itemQty = b.getStringArray("itemQty");

////////////////////////////////////////////////////////
        // create and populate initial build:
////////////////////////////////////////////////////////

        createViews(); // create layout

        orderDataView = (TextView) findViewById((text_viewID[0]));
        orderDataView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        orderDataView.setText("");
        for (int s = 0; s < itemName.length; s++) {
            if (itemName[s] != null) {
                subtotal = String.valueOf(Float.valueOf(itemPrice[s]) * Float.valueOf(itemQty[s]));
                orderDataView.append("Item: " + itemName[s]
                        + " Price: " + itemPrice[s] + " Quantity: " + itemQty[s]
                        + " subTotal: " + subtotal + "\n");
                orderTotal += Float.valueOf(subtotal);
            }
        }

        // Radio Buttons taken care of already
        //
        // subTotal
        subtotalView = (TextView) findViewById((text_viewID[1]));
        subtotalView.setText("sub-total: $" + String.format("%.2f", orderTotal));
        subtotalView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        // tax
        taxView = (TextView) findViewById((text_viewID[2]));
        tax = orderTotal * taxrate;
        taxView.setText("Tax: $" + String.format("%.2f", tax));
        taxView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        //total
        Total = tax+ orderTotal + deliveryCharge;
        totalView = (TextView) findViewById((text_viewID[3]));
        totalView.setText("Total: $" + String.format("%.2f", Total));
        totalView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        // get Card data
        cardView = (EditText) findViewById((edit_viewID[1]));
        cardView.setText("Enter Card Number");
        cardDateView = (EditText) findViewById((edit_viewID[2]));
        cardDateView.setText("Enter Card Date");
        cardVerifyView = (EditText) findViewById((edit_viewID[3]));
        cardVerifyView.setText("Enter Card Security Number");


        submitBtnView = (Button) findViewById(submit_viewID);
        submitBtnView.setText("Submit Order");
////////////////////////////////////////////////
        //respond to button press
////////////////////////////////////////////////
        submitBtnView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                String[] cardInfo = new String[3];

                cardInfo[0] = String.valueOf(cardView.getText() );
                cardInfo[1] = String.valueOf(cardDateView.getText());
                cardInfo[2] = String.valueOf(cardVerifyView.getText());

                Intent i = new Intent(processing.this, confirmation.class);

                i.putExtra("cardInfo", cardInfo);
                i.putExtra("itemName", itemName);
                i.putExtra("itemPrice", itemPrice);
                i.putExtra("itemQty", itemQty);
                i.putExtra("orderTotal", orderTotal);
                i.putExtra("deliveryCharge", deliveryCharge);
                i.putExtra("tax", tax);
                i.putExtra("Total", Total);
                i.putExtra("CardNumber", cardInfo[0]);
                i.putExtra("CardDate", cardInfo[1]);
                i.putExtra("CardVerify", cardInfo[2]);


                startActivity(i);
            }
        });
    }

//////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////

    //create views
    public void createViews() {

        int x = 200, y = 100;
        int i = 0, j = 0;
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);


        createTextView(x, y, rl, text_viewID[i]);
        i++;
        y += 300;

        createRadioButtonView(x, y);

        y += 75;

        //create Radio Button Response EditView
        createEditView(x, y, rl, edit_viewID[j]);
        j++;
        y += 200;

        for ( i = i; i < text_viewID.length; i++) {
            createTextView(x, y, rl, text_viewID[i]);
            y += 100;
        }

        for ( j = j; j < edit_viewID.length; j++) {

            createEditView(x, y, rl, edit_viewID[j]);
            y += 75;
        }
        y += 75;
        createButtonView(x, y, rl, submit_viewID);
        y += 75;

    }

    //////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
    //create textViews
    public void createTextView(int x, int y, RelativeLayout rl, int viewID) {
        // Create a TextView programmatically.
        TextView tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
 //       tv.setX(x);
        tv.setY(y);


        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        // Set text to display in TextView
        tv.setText(" show me ");
        tv.setId(tv.generateViewId());

        int tmpID = viewID;
        viewID = tv.getId();
        text_viewID[tmpID] = viewID;
        // tv.setText(Integer.valueOf(viewNames[0]));
        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor("#000000"));
        // Add newly created TextView to parent view group (RelativeLayout)
        rl.addView(tv);
    }

    //////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////

    // radio button population
    public void createRadioButtonView(int x, int y)
    {
        RadioGroup dynamicRadiogroup = new RadioGroup(this);
        final RadioButton delivery1 = new RadioButton(this);
        final RadioButton carryout1 = new RadioButton(this);
        final RadioButton dinein1 = new RadioButton(this);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(R.id.rl);

        delivery1.setText("Delivery");
        carryout1.setText("Carryout");
        dinein1.setText("Dine In");

        dynamicRadiogroup.addView(delivery1);
        dynamicRadiogroup.addView(carryout1);
        dynamicRadiogroup.addView(dinein1);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        dynamicRadiogroup.setLayoutParams(lp);

        relativelayout.addView(dynamicRadiogroup);

 //       dynamicRadiogroup.setX(x);
        dynamicRadiogroup.setY(y);
        dynamicRadiogroup.setOrientation(LinearLayout.HORIZONTAL);

        dynamicRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub

                deliveryMethod = (EditText) findViewById((edit_viewID[0]));

                if(delivery1.isChecked())
                {
                    deliveryMethod.setText("Enter Address");

                    deliveryCharge = 2.00;
                    subtotalView.setText("Delivery: $" + String.format("%.2f",deliveryCharge)
                            + "\n"+ "sub-total: $" + String.format("%.2f", deliveryCharge + orderTotal));

                    tax = (deliveryCharge+orderTotal) * taxrate;
                    taxView.setText("tax: $" + String.format("%.2f", tax));

                    Total = tax + deliveryCharge + orderTotal;
                    totalView.setText("tax: $" + String.format("%.2f", Total));
                }

                if(carryout1.isChecked())
                {

                    deliveryMethod.setText("Enter time");
                    deliveryCharge = 0.00;
                    subtotalView.setText("sub-total: $" + String.format("%.2f", deliveryCharge
                            + orderTotal));

                    tax = (deliveryCharge+orderTotal) * taxrate;
                    taxView.setText("Tax: $" + String.format("%.2f", tax));

                    Total = tax + deliveryCharge + orderTotal;
                    totalView.setText("Total: $" + String.format("%.2f", Total));
                }

                if(dinein1.isChecked())
                {
                    deliveryMethod.setText("Enter time and date");
                    deliveryCharge = 0.00;
                    subtotalView.setText("sub-total: $" + String.format("%.2f", deliveryCharge
                            + orderTotal));
                    tax = (deliveryCharge+orderTotal) * taxrate;
                    taxView.setText("Tax: $" + String.format("%.2f", tax));

                    Total = tax + deliveryCharge + orderTotal;
                    totalView.setText("Total: $" + String.format("%.2f", Total));
                }
            }
        });

    }

    //////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
    //create EditViews
    public void createEditView(int x, int y, RelativeLayout rl, int viewID) {
        // Create a TextView programmatically.
        EditText tv = new EditText(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
//        tv.setX(x);
        tv.setY(y);


        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        // Set text to display in TextView
        tv.setText(" show me ");
        tv.setId(tv.generateViewId());

        int tmpID = viewID;
        viewID = tv.getId();
        edit_viewID[tmpID] = viewID;
        // tv.setText(Integer.valueOf(viewNames[0]));
        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor("#000000"));
        // Add newly created TextView to parent view group (RelativeLayout)
        rl.addView(tv);
    }

    //////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
    //create Submit Button
    public void createButtonView(int x, int y, RelativeLayout rl, int viewID) {
        // Create a TextView programmatically.
        Button btn = new Button(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
//        btn.setX(x);
        btn.setY(y);


        // Apply the layout parameters to TextView widget
        btn.setLayoutParams(lp);
        // Set text to display in TextView
        btn.setText(" show me ");
        btn.setId(btn.generateViewId());

        int tmpID = viewID;
        viewID = btn.getId();
        submit_viewID = viewID;
        // tv.setText(Integer.valueOf(viewNames[0]));
        // Set a text color for TextView text
        btn.setTextColor(Color.parseColor("#000000"));
        // Add newly created TextView to parent view group (RelativeLayout)
        rl.addView(btn);
    }

    //////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////

}
