package com.example.android.fingerprintdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Main entry point for the sample, showing a backpack and "Purchase" button.
 */
public class MainActivity extends Activity implements OnClickListener {

    EditText Adhar, Name, Marks, Rollno;
    Button View1, btnSignup;
    SQLiteDatabase db;
   /* String encryptedValue;*/

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View1 = (Button) findViewById(R.id.View1);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        Adhar = (EditText) findViewById(R.id.Aadhar2);


        View1.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        // Creating database and table
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
    }
    public void createKey(String keyName, boolean invalidatedByBiometricEnrollment)
    {

    }
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onClick(View view) {
        if (view == View1) {
            //showMessage(">>>","CheckPoint");
            if (Adhar.getText().toString().trim().length() == 0) {
                //showMessage("Error", "Please enter Rollno");
                Toast.makeText(this, "ERROR-PLEASE ENTER Aadhar NUMBER", Toast.LENGTH_LONG).show();
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student17 WHERE Aadhar='" + Adhar.getText() + "'", null);
            if (c.moveToFirst()) {
                // Name.setText(c.getString(1));
                //  Marks.setText(c.getString(2));
                Adhar.setText(c.getString(3));
                //showMessage("Success", "valid Rollno");
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, Display.class);
                startActivity(i);
            } else {
                //showMessage("Error", "Invalid Rollno");
                Toast.makeText(this, "INVALID Aadhar NUMBER", Toast.LENGTH_LONG).show();

                clearText();
            }
        }
        if (view == btnSignup) {
            //showMessage("WELCOME","NOW FILL YOUR FORM");
            Toast.makeText(this, "WELCOME-NOW FILL YOUR FORM", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, Signup.class);
            startActivity(i);
        }

        /*if(view == library)
        {
         Toast.makeText(this,"create login",Toast.LENGTH_LONG).show();
         Intent i = new Intent(MainActivity.this,Main.class);
         startActivity(i);
        }*/
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
        Adhar.setText("");
        Rollno.requestFocus();
    }



}