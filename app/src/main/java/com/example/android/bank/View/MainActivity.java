package com.example.android.bank.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.android.bank.Data.DataBasehandler;
import com.example.android.bank.Data.DataWrapper;
import com.example.android.bank.Data.UserData;
import com.example.android.bank.R;

import java.util.ArrayList;

import static android.app.DatePickerDialog.*;

public class MainActivity extends Activity {

    private final Context context = this;
    private EditText editTextName, editTextDeposit, editTextDob;
    private int year, month, day, amount;
    private String name, dob;
    private DataBasehandler db;
    private ArrayList<UserData> userD;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initializing the UI Elements
        editTextDob = (EditText) findViewById(R.id.input_dob);
        editTextDeposit = (EditText) findViewById(R.id.input_deposit);
        editTextName = (EditText) findViewById(R.id.input_name);
        Button registerButton;
        Button calanderButton;

        registerButton = (Button) findViewById(R.id.register_button);
        calanderButton = (Button) findViewById(R.id.button_calender);


        //Calender button onclicklistner
        calanderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Date picker method
                calanderPicker();
            }
        });

        //Register button onClicklistner
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Reads the respective EditText boxes
                name = editTextName.getText().toString();
                amount = Integer.parseInt(editTextDeposit.getText().toString());
                dob = editTextDob.getText().toString();
                //Function checks if amount is less than 1000
                if (DataTest(amount)) {
                    db = new DataBasehandler(context);

                    regestrationProgressBox();

                    db.addContact(new UserData(name, amount, dob));
                    // db.getContact();

                }


            }
        });

        Button retrieve = (Button) findViewById(R.id.retrieve);

        userD = new ArrayList<UserData>();

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBasehandler sb = new DataBasehandler(context);
                userD = sb.getContact();

              /*  UserData u1 = userD.get(1);
                String user1name=u1.getName();
                Log.i("name in page1",user1name);*/

                //Bundle args = new Bundle();


                Intent listintent = new Intent(MainActivity.this, UserDataList.class);
                listintent.putExtra("asd", new DataWrapper(userD));
                startActivity(listintent);

            }
        });

    }

    /*
     *Method checks if entered amount is less than 1000
     */
    public boolean DataTest(int amount) {

        if (amount <= 1000) {
            UnderBalance();
            Log.i("Amount", "Amount less than 1000");
            return false;
        } else {
            Log.i("Amount", "Amount greater than 1000");
            return true;
        }

    }

    /*
    *Method creates a
    */
    public void UnderBalance() {
        AlertDialog.Builder alertbulider = new AlertDialog.Builder(this);
        alertbulider.setTitle("Warning");
        alertbulider.setMessage("Please deposit above 1000");
        alertbulider.setCancelable(false);
        alertbulider.setPositiveButton("Ok", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = alertbulider.create();
        alertDialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calanderPicker() {
        Calendar cal = Calendar.getInstance();
        int myear = cal.get(Calendar.YEAR);
        int mmonth = cal.get(Calendar.MONTH);
        int mday = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpicker = new DatePickerDialog(this, new OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editTextDob.setText(i2 + "/" + (i1 + 1) + "/" + i);
            }
        }, myear, mmonth, mday);

        dpicker.show();

    }

    public void regestrationProgressBox() {
        final ProgressDialog rounddialog = ProgressDialog.show(MainActivity.this, "Please wait ...", "Registration in progress ...", true);

        rounddialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {

                    Thread.sleep(3000);
                }
                catch (Exception e)
                {

                }
                rounddialog.dismiss();
            }
        }).start();


    }

}
