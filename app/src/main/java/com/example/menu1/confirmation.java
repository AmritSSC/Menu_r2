package com.example.menu1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] viewNames = {"confirmMsg1"};
        int[] viewID = {0, 0, 0}; // List of names of created Views
        createViews(viewID);

        TextView orderData = (TextView) findViewById((viewID[0]));
        orderData.setText(String.valueOf(viewID[0]));


        Bundle b = this.getIntent().getExtras();

//        String[][] orderList =b.getStringArrayList("orderList");
        String[] itemName = b.getStringArray("itemName");
        String[] itemPrice = b.getStringArray("itemPrice");
        String[] itemQty = b.getStringArray("itemQty");
        String[] cardInfo = b.getStringArray("cardInfo");

        String deliveryCharge = b.getString("deliveryCharge");
        String orderTotal = b.getString("orderTotal");
        String Total = b.getString("Total");
        String subtotal = b.getString("subtotal");
        String tax = b.getString("tax");


        orderData.setText("");

        for(int i = 0; i < itemName.length; i++)
        {
            if (itemName[i] != null)
            {
                orderData.append(itemName[i] + "  $" + itemPrice[i] + " Quantity: "
                        + itemQty[i] + " sub-total: $"
                        + (Float.valueOf(itemPrice[i]) * Float.valueOf(itemQty[i]))
                        + "\n");
            }
        }
        orderData.append("\n");


        orderData.append("Order Total: $" + orderTotal + "\n");
        orderData.append("Tax: $" + tax + "\n");
        orderData.append("Delivery Charge: $" + deliveryCharge + "\n");
        orderData.append("Total: $" + Total + "\n");
        orderData.append("\n" + "Card No:" + cardInfo[0] + "\n");
        orderData.append("Card Date: " + cardInfo[1] + "\n");
        orderData.append("Verify: " + cardInfo[2] + "\n");

        orderData.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

    }




    public void createViews(int[] viewID) {

        int x = 200, y = 200;
//        int[][] intArray = new int[item.length+1][2];

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
/*

        for (int row = 0; row < item.length; row++) {

            for (int col = 0; col < item[row].length; col++) {
*/
        // Get the widgets reference from XML layout


        // Create a TextView programmatically.
        TextView tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//        tv.setX(100);
//        tv.setY(y + 50);


        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        // Set text to display in TextView
        tv.setText(" show me ");
        tv.setId(tv.generateViewId());
        viewID[0] = tv.getId();
        // tv.setText(Integer.valueOf(viewNames[0]));
        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor("#000000"));
        // Add newly created TextView to parent view group (RelativeLayout)


        rl.addView(tv);
/*
            }
*/

/*
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText("Quantity: ");
            //
            cb.setId(cb.generateViewId());
            Integer id = (cb.getId());
            intArray[row][0] = id;
 //           cb.setText(intArray.toString());
            cb.setX(x);
            cb.setY(y);
            rl.addView(cb);
            x += 250;

            EditText qty = new EditText(getApplicationContext());
            qty.setText("1");
            qty.setId(qty.generateViewId());
            Integer id2 = (qty.getId());
            intArray[row][1] = id2;
  //          qty.setText(intArray.toString());
            qty.setX(x);
            qty.setY(y-20);
            rl.addView(qty);

            x = 100;
            y += 200;

            // add a check box
        }

        Button slct = new Button(getApplicationContext());
        slct.setText("Select");
        //slct.setId("select1".toInt());

        slct.setId(slct.generateViewId());
        Integer id3 = slct.getId();
        intArray[item.length][0] = id3;
//        slct.setText(id3.toString());
        slct.setX(x);
        slct.setY(y);
        rl.addView(slct);

        return intArray;
*/



    }



}
