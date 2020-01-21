package com.charhabil.contactsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Author: charhabil
//creation de menu

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button _ContactsButton;
        final Button _CRUDButton;
        final Button _SearchButton;
        final Button _PositionButton;
        final Button _AddButton;
        final Button _AboutButton;

        _ContactsButton = findViewById(R.id.btn_machine);
        _CRUDButton = findViewById(R.id.btn_crud);
        _PositionButton = findViewById(R.id.btn_position);
        _AboutButton = findViewById(R.id.btn_about);
        _SearchButton = findViewById(R.id.btn_search);
        _AddButton = findViewById(R.id.btn_add);


        _ContactsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!true) {
                    return;
                }

                _ContactsButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Searching...");
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), ContactsActivity.class));

            }

        });

        _AddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!true) {
                    return;
                }

                _AddButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Searching...");
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), AddContactActivity.class));

            }

        });

        _SearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!true) {
                    return;
                }

                _SearchButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Searching...");
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));

            }

        });

        _CRUDButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!true) {
                    return;
                }

                _CRUDButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Searching...");
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), CRUD_Activity.class));


            }

        });

        _PositionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!true) {
                    return;
                }

                _PositionButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Searching...");
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), PositionActivity.class));


            }

        });

        _AboutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!true) {
                    return;
                }

                _AboutButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Searching...");
                progressDialog.show();
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));

            }

        });



    }
}