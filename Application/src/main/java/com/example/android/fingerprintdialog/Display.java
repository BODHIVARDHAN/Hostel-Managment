package com.example.android.fingerprintdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 *
 */

public class Display extends Activity implements android.view.View.OnClickListener
{
    EditText Rollno,Name,Marks,Aadhar;
    Button Delete,View,ViewAll,btnclear;
    SQLiteDatabase db;
    public Spinner mySpinner;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        mySpinner =(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(Display.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Rollno=(EditText)findViewById(R.id.Rollno);
        Name=(EditText)findViewById(R.id.Name);
        Marks=(EditText)findViewById(R.id.Marks);
        Aadhar=(EditText)findViewById(R.id.Aadhar1);
        //Insert=(Button)findViewById(R.id.Insert);
        //Delete=(Button)findViewById(R.id.Delete);
        //Update=(Button)findViewById(R.id.Update);
        View=(Button)findViewById(R.id.View);
        ViewAll=(Button)findViewById(R.id.ViewAll);
        btnclear = (Button)findViewById(R.id.btnClear);

        //Insert.setOnClickListener(this);
        //Delete.setOnClickListener(this);
       // Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);
        btnclear.setOnClickListener(this);

        // Creating database and table
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student17(rollno VARCHAR,name VARCHAR,marks VARCHAR,Aadhar VARCHAR,course VARCHAR);");
    }
    public void onClick(View view)
    {
        // Inserting a record to the Student table
        /*if(view==Insert)
        {
            // Checking for empty fields
            if(Rollno.getText().toString().trim().length()==0||
                    Name.getText().toString().trim().length()==0||
                    Marks.getText().toString().trim().length()==0||
                    Aadhar.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            String Text = mySpinner.getSelectedItem().toString();
            db.execSQL("INSERT INTO student6 VALUES('"+Rollno.getText()+"','"+Name.getText()+
                    "','"+Marks.getText()+"','"+Aadhar.getText()+"','"+ Text+"');");
            showMessage("Success", "Record added");
            clearText();
        }*/
        // Deleting a record from the Student table

        /*if(view==Delete)
        {
            // Checking for empty roll number
            if(Rollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student17 WHERE rollno='"+Rollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student17 WHERE rollno='"+Rollno.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }*/
        // Updating a record in the Student table
       /* if(view==Update)
        {
            // Checking for empty roll number
            if(Rollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student17 WHERE rollno='"+Rollno.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE student17 SET name='" + Name.getText() + "',marks='" + Marks.getText() +"',Aadhar='"+Aadhar.getText()+
                        "' WHERE rollno='"+Rollno.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }*/
        // Display a record from the Student table
        if(view==View)
        {
            // Checking for empty roll number
            if(Rollno.getText().toString().trim().length()==0)
            {
                //showMessage("Error", "Please enter Rollno");
                Toast.makeText(this,"Please enter Rollno",Toast.LENGTH_LONG).show();
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student17 WHERE rollno='"+Rollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                Name.setText(c.getString(1));
                Marks.setText(c.getString(2));
                Aadhar.setText(c.getString(3));
            }
            else
            {
                //showMessage("Error", "Invalid Rollno");
                Toast.makeText(this,"Error-Invalid Rollno",Toast.LENGTH_LONG).show();
                clearText();
            }
        }
        // Displaying all the records
        if(view==ViewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM student17", null);
            if(c.getCount()==0)
            {
                //showMessage("Error", "No records found");
                Toast.makeText(this,"Error,No records found",Toast.LENGTH_LONG).show();
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Rollno: "+c.getString(0)+"\n");
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("Marks: "+c.getString(2)+"\n");
                buffer.append("Aadhar:"+c.getString(3)+"\n");
                buffer.append("Course: "+c.getString(4)+"\n\n");
            }
            showMessage("Student Details", buffer.toString());
        }
        if(view==btnclear)
        {
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
        Rollno.setText("");
        Name.setText("");
        Marks.setText("");
        Aadhar.setText("");
        Rollno.requestFocus();
    }
}