package com.charhabil.contactsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**Author:charhabil**/
/**pour la recherche des contacts a partir de firebase**/

public class SearchActivity extends AppCompatActivity {

    private ListView listSearch;
    private Button btnSearch;
    private AdapterNumber mAdapter;
    private EditText textSearch;
    private ArrayList<User> mUserArray = new ArrayList<>();
    private ArrayList<User> mSearchArray = new ArrayList<>();
    private ArrayList<User> mArray = new ArrayList<>();
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearch = findViewById(R.id.search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mUserArray.add(new User("Maria", "656465664", "fjoeoz455", "54511d1f5"));
                mUserArray.add(new User("DAD", "0670390155", "fjoeoz455", "54511d1f5"));
                mUserArray.add(new User("Salah", "656465664", "fjoeoz455", "54511d1f5"));
                mUserArray.add(new User("Bob", "656465664", "fjoeoz455", "54511d1f5"));
                mUserArray.add(new User("Aubin", "656465664", "fjoeoz455", "54511d1f5"));
                mUserArray.add(new User("Zahira", "0678515695", "fjoeoz455", "54511d1f5"));
                mUserArray.add(new User("Flora", "656465664", "fjoeoz455", "54511d1f5"));

                listSearch = findViewById(R.id.listSearch);
                textSearch = findViewById(R.id.textSearch);

                mSearchArray = null;
                mSearchArray = getDataSearch(mUserArray,textSearch);
                mAdapter = new AdapterNumber(SearchActivity.this,mSearchArray);
                listSearch.setAdapter(mAdapter);
            }
        });
    }



    public ArrayList<User> getDataSearch(ArrayList<User> mArray, EditText text){
        ArrayList<User> mSearchResult = new ArrayList<>();
        String searchText = text.getText().toString().trim();

        if(searchText.isEmpty()){
            Toast.makeText(SearchActivity.this, "Field Empty", Toast.LENGTH_SHORT).show();
            text.requestFocus();
        }
        else {
            for (User item: mArray) {
                if(searchText.toUpperCase().equals((item.getName().toUpperCase())) || searchText.toUpperCase().equals(item.getNumber().toUpperCase())){
                    mSearchResult.add(item);
                }
            }

            if(mSearchResult.isEmpty()){
                Toast.makeText(SearchActivity.this, "Sorry, No Result for "+searchText, Toast.LENGTH_SHORT).show();
            }else {
                text.setText(null);
            }
        }
        return mSearchResult;
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
