package com.example.android.bank.Data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.bank.R;

import java.io.Serializable;
import java.util.List;

public class UserDataAdapter extends ArrayAdapter<UserData> implements Serializable{


    public UserDataAdapter(Context context, List<UserData> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listview=convertView;

        if (listview==null)
        {
            listview = LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);
        }

        UserData currentud =getItem(position);

        TextView nameview=(TextView)listview.findViewById(R.id.nametextView);
        TextView dobview=(TextView)listview.findViewById(R.id.dobtextView2);
        TextView amountView=(TextView)listview.findViewById(R.id.amounttextView);

        nameview.setText(currentud.getName());
        dobview.setText(currentud.getDob());
        amountView.setText(currentud.getAmount());

        return  listview;
    }
}
