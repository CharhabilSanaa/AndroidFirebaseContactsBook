package com.charhabil.contactsbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**Author : charhabil**/
/**adapter pour les items : contact item**/

public class AdapterNumber extends BaseAdapter {

    Context context;
    ArrayList<User> mUserArray;

    public AdapterNumber(Context c, ArrayList<User> users) {
        this.context = c;
        this.mUserArray = users;
    }


    @Override
    public int getCount() {
        return mUserArray.size();
    }

    @Override
    public Object getItem(int pos) {
        return mUserArray.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        TextView nameUser = (TextView) convertView.findViewById(R.id.itemName);
        TextView numberUser = (TextView) convertView.findViewById(R.id.itemNumber);
        TextView emeiUser = (TextView) convertView.findViewById(R.id.itemEmei);

        final User mUser= (User)this.getItem(position);

        nameUser.setText(mUser.getName().toUpperCase());
        numberUser.setText(mUser.getNumber().toUpperCase());
        emeiUser.setText(mUser.getEmei().toUpperCase());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Your contact : "+mUser.getName()+" has position : "+mUser.getPosition(), Toast.LENGTH_SHORT).show();

            }
        });

        return convertView;
    }
}