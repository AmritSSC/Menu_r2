package com.example.menu1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class acknowledge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        // Create a TextView programmatically.
        TextView tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        // Set text to display in TextView
        tv.setText(" Allright, this page to send data!!! ");
        tv.setId(tv.generateViewId());
//        viewID[0] = tv.getId();
        // tv.setText(Integer.valueOf(viewNames[0]));
        // Set a text color for TextView text
        tv.setTextColor(Color.parseColor("#000000"));
        // Add newly created TextView to parent view group (RelativeLayout)


        rl.addView(tv);

    }
}
