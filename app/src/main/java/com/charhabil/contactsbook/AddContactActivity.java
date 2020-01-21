package com.charhabil.contactsbook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**Author:charhabil**/
/**ajout d'un nouveau contacts -- inscription**/

public class AddContactActivity extends AppCompatActivity {

    DatabaseReference myReference = null;
    EditText textName = null;
    EditText textNumber = null;
    Button btnSave = null;
    Intent intent = null;
    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        myReference = FirebaseDatabase.getInstance().getReference("numberBookq");
        textName = findViewById(R.id.textName);
        textNumber = findViewById(R.id.itemNumber);
        btnSave = findViewById(R.id.save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                onSave();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSave() {

        String name = textName.getText().toString().trim();
        String number = textNumber.getText().toString().trim();
        String emei = String.valueOf(getBaseContext().getSystemService(Context.TELEPHONY_SERVICE)).trim();
        String position = String.valueOf(GetLocation()).trim();

        //verification:
        if (name.isEmpty()) {
            Toast.makeText(AddContactActivity.this, "Name Empty", Toast.LENGTH_SHORT).show();
            textName.requestFocus();
        } else if (number.isEmpty()) {
            Toast.makeText(AddContactActivity.this, "Number Empty", Toast.LENGTH_SHORT).show();
            textNumber.requestFocus();
        } else {
            //au cas ou l'envoi est reussi
            User user = new User(name, number, emei, position);
            myReference.push().setValue(user);

            Toast.makeText(AddContactActivity.this, "Success", Toast.LENGTH_SHORT).show();
            intent = new Intent(AddContactActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    //connaitre la localisation:
    public Location[] GetLocation() {
        final Location[] locationUser = {null};
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }

        //acceder a la location
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 150, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationUser[0] = location;
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                altitude = location.getAltitude();
                accuracy = location.getAccuracy();
                String msg = String.format(getResources().getString(R.string.new_location), latitude,longitude, altitude, accuracy);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                String newStatus = "";
                switch (status) {
                    case LocationProvider.OUT_OF_SERVICE:
                        newStatus = "OUT_OF_SERVICE";
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        newStatus = "TEMPORARILY_UNAVAILABLE";
                        break;
                    case LocationProvider.AVAILABLE:
                        newStatus = "AVAILABLE";
                        break;
                }
                String msg = String.format(getResources().getString(R.string.provider_new_status), provider, newStatus);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(String provider) {
                String msg = String.format(getResources().getString(R.string.provider_enabled), provider);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                String msg = String.format(getResources().getString(R.string.provider_disabled), provider);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return locationUser;
    }
}
