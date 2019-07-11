package com.example.hellofirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText etTexto;
    Button btnEnviar;
    TextView tvFirebase;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database

        etTexto = findViewById(R.id.etFirebase);
        btnEnviar = findViewById(R.id.btnFirebase);
        tvFirebase = findViewById(R.id.tvFirebase);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadena = etTexto.getText().toString();
                myRef.setValue(cadena);

            }

        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                This method is called once with the initial value and again
                whenever data at this location is updated.
                */
                String value = dataSnapshot.getValue(String.class);
                tvFirebase.setText(value);
                myRef.setValue(value);
                Log.d("XXX", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("XXX", "Failed to read value.", error.toException());
            }
        });


    }
}
