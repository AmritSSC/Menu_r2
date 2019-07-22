package com.example.menu1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[][] item = new String[7][3];
        item[0][0] = "Item 1";
        item[1][0] = "Item 2";
        item[2][0] = "Item 3";
        item[3][0] = "Item 4";
        item[4][0] = "Item 5";
        item[5][0] = "Item 6";
        item[6][0] = "Item 7";

        item[0][1] = "01.00";
        item[1][1] = "10.00";
        item[2][1] = "20.00";
        item[3][1] = "05.00";
        item[4][1] = "10.00";
        item[5][1] = "20.00";
        item[6][1] = "05.00";


        item[0][2] = "Description 1";
        item[1][2] = "Description 2";
        item[2][2] = "Description 3";
        item[3][2] = "Description 4";
        item[4][2] = "Description 5";
        item[5][2] = "Description 6";
        item[6][2] = "Description 7";

        final int[][] orderList = createMenuList(item);

        final Button button = findViewById(orderList[orderList.length-1][0]);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
/*              // check that button press is working- it is
                SystemClock.sleep(3000);
                android.os.Process.killProcess(android.os.Process.myPid());
*/

//                String[][] sendList= new String[orderList.length][3];
                String[] sendItem= new String[orderList.length];
                String[] sendPrice= new String[orderList.length];
                String[] sendQty= new String[orderList.length];



                int arraytrack = 0; // track valu
                // e

                for (int list = 0; list <(orderList.length-1) ; list ++)
                {
                    CheckBox chbox = (CheckBox) findViewById(orderList[list][0]);
                    EditText numbox = (EditText) findViewById(orderList[list][1]);
                    String amountOrdered = numbox.getText().toString();
//                    float costper = Float.parseFloat(item[list][2]);
//                    float numOrdered = Float.parseFloat(numberOrdered);

                    if (chbox.isChecked())
                    {
/*
                         SystemClock.sleep(3000);
                        android.os.Process.killProcess(android.os.Process.myPid());
*/
//                        sendList[arraytrack][0] = item[list][0];
//                        sendList[arraytrack][1] = item[list][2];
//                        sendList[arraytrack][2] = amountOrdered;


                        sendItem[arraytrack] = item[list][0]; //itemname
                        sendPrice[arraytrack] = item[list][2]; //item price
                        sendQty[arraytrack] = amountOrdered; //item amount
                        arraytrack++;
                    }
                }

                Intent i = new Intent(MainActivity.this, processing.class);
                Bundle mBundle = new Bundle();
//                mBundle.putStringArrayList("orderList",  sendList);
//                i.putExtras(mBundle);


                i.putExtra("itemName", sendItem);
                i.putExtra("itemPrice", sendPrice);
                i.putExtra("itemQty", sendQty);


                startActivity(i);

            }
            // read dynamically generated checkboxes

        });

    }


    public int[][] createMenuList(String[][] item) {

        int[][] intArray = new int[item.length+1][2];


        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

        // Create a LayoutParams for TextView
        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, // Width of TextView
                LayoutParams.WRAP_CONTENT); // Height of TextView
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        LayoutParams lp2 = new LayoutParams(
                LayoutParams.MATCH_PARENT, // Width of TextView
                LayoutParams.WRAP_CONTENT); // Height of TextView
        lp2.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        LayoutParams lp3 = new LayoutParams(
                LayoutParams.WRAP_CONTENT, // Width of TextView
                LayoutParams.MATCH_PARENT); // Height of TextView
        lp3.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        LayoutParams lp4 = new LayoutParams(
                LayoutParams.MATCH_PARENT, // Width of TextView
                LayoutParams.MATCH_PARENT); // Height of TextView
        lp4.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);


        RelativeLayout.LayoutParams rLParams =
                new RelativeLayout.LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        rLParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
/////////////////////// Scroll view setup:
        ScrollView sv = new ScrollView(this);
        sv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        ll.setOrientation(LinearLayout.VERTICAL);
//        lp.LayoutParams(l1);
        sv.addView(ll);


//        float x1 = 100, y1 = 200;
        for (int row = 0; row < item.length; row++) {
            for (int col = 0; col < item[row].length; col++) {
                // Get the widgets reference from XML layout

                // Create a TextView programmatically.
                TextView tv = new TextView(getApplicationContext());


/*
    // set description to same row as others
                tv.setX(x); tv.setY(y);
                if (col != 1) x += 150;
                else    x += 400;
*/
                // Set text to display in TextView
                tv.setText(item[row][col]);
                // Set a text color for TextView text
                tv.setTextColor(Color.parseColor("#ff0000"));
                ll.addView(tv);
//                rl.addView(tv);
              }


            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText("Quantity: ");
            cb.setId(cb.generateViewId());
            Integer id = (cb.getId());
            intArray[row][0] = id;

//            cb.setX(x1);
//            cb.setY(y1);
  //          cb.setLayoutParams(lp);
            ll.addView(cb);
//            x += 250;

            EditText qty = new EditText(getApplicationContext());
            qty.setText("1");
            qty.setId(qty.generateViewId());
            Integer id2 = (qty.getId());
            intArray[row][1] = id2;
  //          qty.setText(intArray.toString());

 //           qty.setX(x1);
 //           qty.setY(y1-20);
//            qty.setLayoutParams(lp);
            ll.addView(qty);
            //rl.addView(qty);

   //         x1 = 100;
     //       y1 += 200;
            // add a check box
        }
//        rl.removeView(sv);

        rl.addView(sv);



        Button slct = new Button(getApplicationContext());
        slct.setText("Select");
        //slct.setId("select1".toInt());

        slct.setId(slct.generateViewId());
        Integer id3 = slct.getId();
        intArray[item.length][0] = id3;

        rl.addView(slct, rLParams);


        return intArray;

    }



}

