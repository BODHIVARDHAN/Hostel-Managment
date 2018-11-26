package com.example.android.fingerprintdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Signup extends Activity implements View.OnClickListener {
    TextView sd, rl, nm, mr, ad;
    EditText Rollno, Name, Marks, Aadhar;
    Button Department, OK, sub, Insert, btnclear, Room;
    SQLiteDatabase db;
    public Spinner mySpinner;
    public Spinner mySpinner1;
    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;
    RadioButton mca,mcs,mtech,sem1,sem2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(Signup.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup2.clearCheck();
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) group.findViewById(checkedId);
                if (null != rb1 && checkedId > -1) {
                    Toast.makeText(Signup.this, rb1.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        mySpinner = (Spinner) findViewById(R.id.spinner1);
        mySpinner1 = (Spinner) findViewById(R.id.spinner6);


        mca=(RadioButton)findViewById(R.id.mca);
        mcs=(RadioButton)findViewById(R.id.mcs);
        mtech=(RadioButton)findViewById(R.id.mtech);

        sem1=(RadioButton)findViewById(R.id.sem1);
        sem2=(RadioButton)findViewById(R.id.sem2);



        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Signup.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Signup.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names1));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);


        mca.setOnClickListener(this);
        mcs.setOnClickListener(this);
        mtech.setOnClickListener(this);

        sem1.setOnClickListener(this);
        sem2.setOnClickListener(this);


        Department = (Button) findViewById(R.id.Department);
        Department.setOnClickListener(this);
        Department.setVisibility(View.GONE);
        Department.setVisibility(View.VISIBLE);

        Room = (Button) findViewById(R.id.Room);
        Room.setOnClickListener(this);
        OK = (Button) findViewById(R.id.OK);
        OK.setOnClickListener(this);
        OK.setVisibility(View.GONE);
        OK.setVisibility(View.INVISIBLE);
        sub = (Button) findViewById(R.id.sub);
        sub.setOnClickListener(this);
        sub.setVisibility(View.GONE);
        sub.setVisibility(View.INVISIBLE);

        radioGroup.setOnClickListener(this);
        radioGroup.setVisibility(View.GONE);
        radioGroup.setVisibility(View.INVISIBLE);

        radioGroup2.setOnClickListener(this);
        radioGroup2.setVisibility(View.GONE);
        radioGroup2.setVisibility(View.INVISIBLE);

        sd = (TextView) findViewById(R.id.sd);
        sd.setVisibility(View.GONE);
        sd.setVisibility(View.INVISIBLE);

        rl = (TextView) findViewById(R.id.rl);
        rl.setVisibility(View.GONE);
        rl.setVisibility(View.INVISIBLE);

        nm = (TextView) findViewById(R.id.nm);
        nm.setVisibility(View.GONE);
        nm.setVisibility(View.INVISIBLE);

        mr = (TextView) findViewById(R.id.mr);
        mr.setVisibility(View.GONE);
        mr.setVisibility(View.INVISIBLE);

        ad = (TextView) findViewById(R.id.ad);
        ad.setVisibility(View.GONE);
        ad.setVisibility(View.INVISIBLE);





        Name = (EditText) findViewById(R.id.Name);
        Name.setVisibility(View.INVISIBLE);

        Marks = (EditText) findViewById(R.id.Marks);
        Marks.setVisibility(View.INVISIBLE);

        mySpinner.setVisibility(View.INVISIBLE);
        mySpinner1.setVisibility(View.INVISIBLE);

        Aadhar = (EditText) findViewById(R.id.Aadhar1);
        Aadhar.setVisibility(View.INVISIBLE);

        Rollno = (EditText) findViewById(R.id.Rollno);
        Rollno.setVisibility(View.INVISIBLE);


        Insert = (Button) findViewById(R.id.Insert);
        Insert.setVisibility(View.INVISIBLE);

        btnclear = (Button) findViewById(R.id.btnClear);
        btnclear.setVisibility(View.INVISIBLE);




        Insert.setOnClickListener(this);
        btnclear.setOnClickListener(this);

        // Creating database and table
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student17(rollno VARCHAR ,name VARCHAR,marks VARCHAR,Aadhar VARCHAR ,department VARCHAR,course VARCHAR,Year VARCHAR,SEM VARCHAR);");


        // Creating database and table
    }

    public void onClick(View view) {
        // Inserting a record to the Student table
        if (view == Department) {

            String Text = mySpinner.getSelectedItem().toString();
            mySpinner.setVisibility(View.VISIBLE);
            OK.setVisibility(View.VISIBLE);

//            clearText();
        }

        if (view == OK) {

            radioGroup.setVisibility(View.VISIBLE);
            sub.setVisibility(View.VISIBLE);

            String Text1 = mySpinner1.getSelectedItem().toString();
            mySpinner1.setVisibility(View.VISIBLE);

            radioGroup2.setVisibility(View.VISIBLE);
            sub.setVisibility(View.VISIBLE);


//            clearText();
        }
        if (view == sub) {
            sd.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            nm.setVisibility(View.VISIBLE);
            mr.setVisibility(View.VISIBLE);
            ad.setVisibility(View.VISIBLE);
            //finger.setVisibility(View.VISIBLE);
            Insert.setVisibility(View.VISIBLE);

           // Scan.setVisibility(View.VISIBLE);
            btnclear.setVisibility(View.VISIBLE);
            Rollno.setVisibility(View.VISIBLE);
            Aadhar.setVisibility(View.VISIBLE);
            Name.setVisibility(View.VISIBLE);

            Marks.setVisibility(View.VISIBLE);


        }
        if(view==Room)
        {

            Intent i=new Intent(Signup.this,Hostel_Form.class);
                    startActivity(i);
        }
        if (view == Insert) {
            // Checking for empty fields
            if (Rollno.getText().toString().trim().length() == 0 ||
                    Name.getText().toString().trim().length() == 0 ||
                    Marks.getText().toString().trim().length() == 0 ||
                    Aadhar.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter all values");
                return;
            }
            String num = Aadhar.getText().toString();
            boolean result = Verhoeff.validateVerhoeff(num);
            String msg = String.valueOf(result);

            if (msg == "true") {
                Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(getApplicationContext(), "Please enter the valid number", Toast.LENGTH_LONG).show();
                showMessage("false", "Please enter valid card Number");

                return;


            }


            String Text = mySpinner.getSelectedItem().toString();
            //String Text1 = mySpinner1.getSelectedItem().toString();

            int rb = radioGroup.getCheckedRadioButtonId();
            String val;
            if(rb==0)
            {
                val="mca";
            }
            else if(rb==1)
            {
                val="mcs";
            }

            else
            {
                val="mtech";
            }


            String Text1 = mySpinner1.getSelectedItem().toString();


            String val1="";
            int rb1 = radioGroup2.getCheckedRadioButtonId();
            if(rb1==R.id.sem1)
            {
                val1="sem1";

            }
            if(rb1==R.id.sem2)
            {
                val1="sem2";

            }

            db.execSQL("INSERT INTO student17 VALUES('" + Rollno.getText() + "','" + Name.getText() +
                    "','" + Marks.getText() + "','" + Aadhar.getText() + "','" + Text + "','"+val+"','"+Text1+"','"+val1+"');");
            showMessage("Success", "Record added");
            clearText();
        }

        if (view == btnclear) {
            clearText();
        }
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        Rollno.setText("");
        Name.setText("");
        Marks.setText("");
        Aadhar.setText("");
        Rollno.requestFocus();
    }

}