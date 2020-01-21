package com.charhabil.contactsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**Auhtor: Charhabil**/
/**insertion des donnees des contacts**/
/**affichage des contacts from firebase**/

public class CRUD_Activity extends AppCompatActivity {

    private ListView listView;
    private AdapterNumber mAdapter;
    private ArrayList<User> mArray = new ArrayList<>();
    private ArrayList<User> mUserArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_);

        listView = findViewById(R.id.listNumber);

        //insertion des donnees vers firebase //

        mUserArray.add(new User("Maria", "066481664", "asoeoz785", "94511p195"));
        mUserArray.add(new User("DAD", "0670390155", "kjoeom455", "5452133f5"));
        mUserArray.add(new User("Salah", "066465664", "jjonbz415", "845e6e1f5"));
        mUserArray.add(new User("Mom", "0678515695", "pe87oz111", "545ui81f0"));
        mUserArray.add(new User("Aubin", "066465664", "5f986z455", "94522d198"));
        mUserArray.add(new User("Zahira", "0678515695", "7ioeoz335", "88517d1fg"));
        mUserArray.add(new User("Flora", "656465664", "fmo7845222", "44111ddr5"));
        mUserArray.add(new User("Leila", "06745586525", "fj788z457", "545ui81f0"));
        mUserArray.add(new User("Mohammed", "0623355684", "45pooz985", "545ui81f0"));


        mAdapter = new AdapterNumber(this,mUserArray);
        listView.setAdapter(mAdapter);
    }

    public ArrayList<User> retrieve()
    {
        FirebaseDatabase.getInstance().getReference("numberBook").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                mArray.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String name = ds.child("name").getValue(String.class);
                    String number = ds.child("number").getValue(String.class);
                    String emei = ds.child("emei").getValue(String.class);
                    String position = ds.child("position").getValue(String.class);

                    mArray.add(new User(name,number,emei,position));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                mArray.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    User user = ds.getValue(User.class);
                    mArray.add(user);
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return mArray;
    }
}
