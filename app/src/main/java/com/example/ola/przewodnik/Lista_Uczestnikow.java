package com.example.ola.przewodnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Lista_Uczestnikow extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    ListView lv;
    FirebaseListAdapter adapterlist;
    private String lon, lan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__uczestnikow);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grupy, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String  grupa2 = parent.getItemAtPosition(position).toString();


        lv = (ListView) findViewById(R.id.listView);
        //zmiana
        // String currentuser = FirebaseAuth.getInstance().getUid();
        //Integer a = Integer.valueOf(FirebaseDatabase.getInstance().getReference().child("przewodnik").child("grupa"));
        Query query = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("grupa").equalTo(grupa2);

        //Query query = FirebaseDatabase.getInstance().getReference().child("users");
        FirebaseListOptions<User> options = new FirebaseListOptions.Builder<User>()
                .setLayout(R.layout.user)
                .setQuery(query, User.class)
                .build();

        adapterlist = new FirebaseListAdapter(options){
            @Override
            protected void populateView(View v, Object model, int position) {
                //TextView userID = v.findViewById(R.id.userID);
                TextView imie = v.findViewById(R.id.imie);
                TextView nazwisko = v.findViewById(R.id.nazwisko);
                TextView email = v.findViewById(R.id.email);
                TextView phone = v.findViewById(R.id.phone);
                TextView grupa = v.findViewById(R.id.grupa);

                User std = (User) model;

                // userID.setText(std.getUserID().toString());
                imie.setText("Imie: " + std.getImie().toString());
                nazwisko.setText("Nazwisko: "+ std.getNazwisko().toString());
                email.setText("Email: "+ std.getNazwisko().toString());
                phone.setText("Telefon: "+ std.getPhone().toString());
                grupa.setText("Numer Grupy: "+std.getGrupa().toString());



            }


        };
        lv.setAdapter(adapterlist);
        adapterlist.startListening();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        lon ="14.5400964";
                        lan = "53.4282337";
                        showUser(lon, lan);

                    case 1:
                        lon ="14.536621";
                        lan = "53.4305932";
                        showUser(lon, lan);

                    case 2:
                        lon ="14.5350224";
                        lan = "53.4322551";
                        showUser(lon, lan);

                    case 3:
                        lon ="14.4912453";
                        lan = "53.4479143";
                        showUser(lon, lan);

                }
            }
        });

        lv.setAdapter(adapterlist);
        adapterlist.startListening();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onStop(){
        super.onStop();

        adapterlist.stopListening();
    }


    public void showMe(View view){
        Intent intencja = new Intent(this, GetLocation.class);
        startActivity(intencja);
    }

    public void showUser(String lon, String lan){
        Intent intencja = new Intent(this, MapsActivity.class);
        TextView textViewLo = (TextView) findViewById(R.id.lon);
        //intencja.putExtra("Longitude", "14.5937644");
        intencja.putExtra("Longitude",lon );
        TextView textViewLa = (TextView) findViewById(R.id.lat);
        //intencja.putExtra("Longitude", "53.4682782");
        intencja.putExtra("Latitude", lan);
        startActivity(intencja);
    }

}

