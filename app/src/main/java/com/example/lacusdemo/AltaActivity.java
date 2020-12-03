package com.example.lacusdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AltaActivity extends AppCompatActivity {

    long n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        Button[] btnAction = {
                (Button) findViewById(R.id.btnBorrar),
                (Button) findViewById(R.id.btnAñadir)
        };
        final EditText[] txtRecive = {
                (EditText) findViewById(R.id.edtPlacas),
                (EditText) findViewById(R.id.edtMarca),
                (EditText) findViewById(R.id.edtPeso),
                (EditText) findViewById(R.id.edtCarga),
                (EditText) findViewById(R.id.edtModelo)
        };


        btnAction[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText txt : txtRecive)
                    txt.setText("");
            }
        });

        btnAction[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFireBaseItems();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Camion" + n); //Añadir lectura de camion por numeracion

                List<String> Truck = new ArrayList<String>();
                for (EditText txt : txtRecive)
                    Truck.add(txt.getText().toString());
                Truck.add("New");
                Truck.add("-34");    //Cambiar por variables de ubicacion actual
                Truck.add("151");

                myRef.setValue(Truck);
                for (EditText txt : txtRecive)
                    txt.setText("");
            }
        });
    }

    void getFireBaseItems() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                n = dataSnapshot.getChildrenCount() - 1;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //Toast.makeText(this, "Error on consult", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
