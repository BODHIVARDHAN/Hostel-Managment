package com.example.android.fingerprintdialog;

//package com.example.android.fingerprintdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 *
 */

public class RoomDetails extends Activity implements android.view.View.OnClickListener{
    EditText Rfan,Rtube,Rbulb,Rlocker,Rtable,Rother,Rchair,Rbed,Rproblem;
    Button Rsubmit;
    SQLiteDatabase db;
    private RadioGroup radioGroup,radioGroup1,radioGroup2,radioGroup3;
    RadioButton fan_working,fan_notworking,tube_working,tube_notworking,bulb_working,bulb_notworking,locker_working,locker_notworking;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomdetails);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(RoomDetails.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup1.clearCheck();
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_t = (RadioButton) group.findViewById(checkedId);
                if (null != rb_t && checkedId > -1) {
                    Toast.makeText(RoomDetails.this, rb_t.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_bulb = (RadioButton) group.findViewById(checkedId);
                if (null != rb_bulb && checkedId > -1) {
                    Toast.makeText(RoomDetails.this, rb_bulb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup3.clearCheck();
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb_l = (RadioButton) group.findViewById(checkedId);
                if (null != rb_l && checkedId > -1) {
                    Toast.makeText(RoomDetails.this, rb_l.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });



        fan_working = (RadioButton) findViewById(R.id.fan_working);
        fan_notworking = (RadioButton) findViewById(R.id.fan_notworking);
        fan_working.setOnClickListener(this);
        fan_notworking.setOnClickListener(this);

        tube_working = (RadioButton) findViewById(R.id.tube_working);
        tube_notworking = (RadioButton) findViewById(R.id.tube_notworking);
        tube_working.setOnClickListener(this);
        tube_notworking.setOnClickListener(this);

        bulb_working = (RadioButton) findViewById(R.id.bulb_working);
        bulb_notworking = (RadioButton) findViewById(R.id.bulb_notworking);
        bulb_working.setOnClickListener(this);
        bulb_notworking.setOnClickListener(this);

        locker_working = (RadioButton) findViewById(R.id.locker_working);
        locker_notworking = (RadioButton) findViewById(R.id.locker_notworking);
        locker_working.setOnClickListener(this);
        locker_notworking.setOnClickListener(this);



        Rfan = (EditText) findViewById(R.id.Rfan);
        Rtube = (EditText) findViewById(R.id.Rtube);
        Rbulb = (EditText) findViewById(R.id.Rbulb);
        Rlocker = (EditText) findViewById(R.id.Rlocker);
        Rtable = (EditText) findViewById(R.id.Rtable);
        Rother = (EditText) findViewById(R.id.Rother);
        Rchair = (EditText) findViewById(R.id.Rchair);
        Rbed = (EditText) findViewById(R.id.Rbed);
        Rproblem = (EditText) findViewById(R.id.Rproblem);
        radioGroup.setOnClickListener(this);
        radioGroup1.setOnClickListener(this);
        radioGroup2.setOnClickListener(this);
        radioGroup3.setOnClickListener(this);
        Rsubmit = (Button) findViewById(R.id.Rsubmit);
        Rsubmit.setOnClickListener(this);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS RoomDetails17(Rfan VARCHAR ,Rtube VARCHAR,Rbulb VARCHAR,Rlocker VARCHAR ,Rtable VARCHAR,Rother VARCHAR,Rchair VARCHAR,Rbed VARCHAR,Rproblem VARCHAR,Fan VARCHAR,TUBE VARCHAR,BULB VARCHAR,LOCKER VARCHAR);");

    }
    @Override
    public void onClick(View view) {
        if(view==Rsubmit)
        {
            // Checking for empty fields
            if(Rfan.getText().toString().trim().length()==0||
                    Rtube.getText().toString().trim().length()==0||
                    Rbulb.getText().toString().trim().length()==0||
                    Rlocker.getText().toString().trim().length()==0||
                    Rtable.getText().toString().trim().length()==0||
                    Rother.getText().toString().trim().length()==0||
                    Rchair.getText().toString().trim().length()==0||
                    Rbed.getText().toString().trim().length()==0||
                    Rproblem.getText().toString().trim().length()==0) {
                showMessage("Error", "Please enter all values");
                return;
            }
            String value="";
            String val_t="";
            String val_bulb="";
            String val_l="";
            //String val_tab="";
            //String val_c="";


            int rb = radioGroup.getCheckedRadioButtonId();
            int rb_t = radioGroup1.getCheckedRadioButtonId();
            int rb_bulb = radioGroup2.getCheckedRadioButtonId();
            int rb_l = radioGroup3.getCheckedRadioButtonId();



            if(rb==R.id.fan_working)
            {
                value="fan_working";
            }
            else if(rb==R.id.fan_notworking)
            {
                value="fan_notworking";
            }

            if(rb_t==R.id.tube_working)
            {
                val_t="tube_working";
            }
            else if(rb_t==R.id.tube_notworking)
            {
                val_t="tube_notworking";
            }

            if(rb_bulb==R.id.bulb_working)
            {
                val_bulb="bulb_working";
            }
            else if(rb_bulb==R.id.bulb_notworking)
            {
                val_bulb="bulb_notworking";
            }

            if(rb_l==R.id.locker_working)
            {
                val_l="locker_working";
            }
            else if(rb_l==R.id.locker_notworking)
            {
                val_l="locker_notworking";
            }

            db.execSQL("INSERT INTO RoomDetails17 VALUES('"+Rfan.getText()+"','"+Rtube.getText()+"','"+Rbulb.getText()+
                    "','"+Rlocker.getText()+"','"+Rtable.getText()+"','"+ Rother.getText()+"','"+Rchair.getText()+"','"+Rbed.getText()+"','"+Rproblem.getText()+"','"+value+"','"+val_t+"','"+val_bulb+"','"+val_l+"');");
            showMessage("NOW YOU CAN USE ROOM","Record added");
            clearText();
        }
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        Rfan.setText("");
        Rtube.setText("");
        Rbulb.setText("");
        Rlocker.setText("");
        Rtable.setText("");
        Rother.setText("");
        Rchair.setText("");
        Rbed.setText("");
        Rproblem.setText("");
        Rtube.requestFocus();
    }
}