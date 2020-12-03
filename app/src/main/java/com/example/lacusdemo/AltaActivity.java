package com.example.lacusdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AltaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                for (EditText btn : txtRecive)
                    btn.setText("");
            }
        });
    }
}
