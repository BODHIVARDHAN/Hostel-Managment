package com.example.android.fingerprintdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.example.android.fingerprintdialog.R.id.btnSignup;


public class Hostel_Form extends AppCompatActivity implements View.OnClickListener {
    EditText RollnoH;
    Button ok;
    //Spinner mySpinner1;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostel_form);

        ok = (Button) findViewById(R.id.ok);

        RollnoH= (EditText) findViewById(R.id.RollnoH);

        ok.setOnClickListener(this);
        RollnoH.setOnClickListener(this);

        // Creating database and table
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);



    }
    public void onClick(View view)
    {
        if (view == ok) {
            //showMessage(">>>","CheckPoint");
            if(RollnoH.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Roll Number");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student17 WHERE rollno='"+RollnoH.getText()+"'", null);
            if(c.moveToFirst())
            {
                // Name.setText(c.getString(1));
                //  Marks.setText(c.getString(2));
                RollnoH.setText(c.getString(0));
                showMessage("Success", "valid Roll Number");
                Intent i = new Intent(Hostel_Form.this, RoomDetails.class);
                startActivity(i);
            }
            else
            {
                showMessage("Error", "Invalid Roll Number");
                clearText();
            }
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
        RollnoH.setText("");
        //ok.setText("");
        RollnoH.requestFocus();
    }
}