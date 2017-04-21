package com.example.android.bank.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.android.bank.Data.DataWrapper;
import com.example.android.bank.Data.UserData;
import com.example.android.bank.Data.UserDataAdapter;
import com.example.android.bank.R;

import java.util.ArrayList;

public class UserDataList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_list);

        //Intent intent= getIntent();
        //ArrayList<UserData> args= (ArrayList<UserData>) getIntent().getSerializableExtra("asd");
       // ArrayList<UserData> ud= (ArrayList<UserData>) args.getSerializable("ARRAYLIST");
        /*UserData u1 = ud.get(0);
        String user1name=u1.getName();*/
        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("asd");
        ArrayList<UserData> list = dw.getParliaments();

        int i = list.size();
        Log.i("number of elements",Integer.toString(i));



        UserDataAdapter uda= new UserDataAdapter(this,list);
        ListView ls = (ListView)findViewById(R.id.activity_user_data_list);

        ls.setAdapter(uda);


    }
}
