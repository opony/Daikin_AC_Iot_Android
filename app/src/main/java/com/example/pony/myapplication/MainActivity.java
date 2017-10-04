package com.example.pony.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import okhttp3.OkHttpClient;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
//    private boolean isCoolingMode = true;
    MainActivity main = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NumberPicker   pick=(NumberPicker) findViewById(R.id.numberPicker2);
        pick.setMaxValue(35);
        pick.setMinValue(22);
        pick.setValue(27);

        main = this;



        RadioGroup rGroup = (RadioGroup)findViewById(R.id.radGrp);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);

                final String mode = (String)checkedRadioButton.getText();

                AlertDialog.Builder builder = new AlertDialog.Builder(main);
                builder.setMessage("You changed the mode to [" + mode + "]")
                        .setTitle("Warning!");
                // Add the buttons
                //builder.setNegativeButton()
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });

//                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        RadioButton resetBtn = null;
//                        if(mode.equals("Cooling")){
//                            resetBtn = (RadioButton)findViewById(R.id.heatRad);
//                        }else{
//                            resetBtn = (RadioButton)findViewById(R.id.coolRad);
//                        }
//
//                        resetBtn.setChecked(true);
//                    }
//                });

                AlertDialog dialog = builder.create();
                dialog.show();

                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();

                Log.v(TAG, getResources().getResourceEntryName(checkedRadioButton.getId()) + " : " + isChecked);
            }
        });
    }

    public void sayHello(View view){

        Log.v(TAG, getResources().getResourceEntryName(view.getId()) + " : change text to abc");

    }

    public void setTemp(View view){
        Button b = (Button)view;
        String text = b.getText().toString();
        NumberPicker   pick=(NumberPicker) findViewById(R.id.numberPicker2);
        int tmpVal = pick.getValue();
        if(text == "+"){
            tmpVal++;
            if(tmpVal > pick.getMaxValue()){
                tmpVal = pick.getMinValue();
            }

        }else{
            tmpVal--;
            if(tmpVal < pick.getMinValue()){
                tmpVal = pick.getMaxValue();
            }
        }
        pick.setValue(tmpVal);
        Log.i(TAG, "click [" + text +"] , set temp to [" + pick.getValue() + "]");
    }
}
