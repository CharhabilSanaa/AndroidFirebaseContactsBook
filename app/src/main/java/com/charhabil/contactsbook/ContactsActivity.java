package com.charhabil.contactsbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.charhabil.contactsbook.R;

//Author : charhabil
//Affichage des liste de contacts a partir de smartphone**/

public class ContactsActivity extends AppCompatActivity {


    private static final int RESULT_PICK_CONTACTS=1;
    private TextView phone;
    private Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        phone = findViewById(R.id.phone);
        select = findViewById(R.id.select);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(in,RESULT_PICK_CONTACTS);
            }
        });

    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            switch (requestCode) {
                case RESULT_PICK_CONTACTS:
                    contactPicked(data);
                    break;
            }
        }else{
            Toast.makeText(this,"Failed to pick contacts",Toast.LENGTH_LONG).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor=null;
        try{
            String phoneNo=null;
            Uri uri=data.getData();
            cursor=getContentResolver().query(uri,null,null,null,null);
            cursor.moveToFirst();
            int phoneIndex= cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            phoneNo=cursor.getString(phoneIndex);
            phone.setText(phoneNo);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
