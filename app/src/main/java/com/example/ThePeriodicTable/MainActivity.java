package com.example.ThePeriodicTable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;


import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private Object inputStream;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);


        TextView txt = findViewById(R.id.textView);
        EditText edt = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);
        Button reset_btn = findViewById(R.id.reset_button);
        ImageView img = findViewById(R.id.imageView);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ur = "https://i.imgur.com/tGbaZCY.jpg";
                Picasso.get().load(ur).into(img);
            }
        });




btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String  element_to_find = edt.getText().toString();
        vibe.vibrate(80);

        inputStream = getResources().openRawResource(R.raw.periodic_table);
        BufferedReader br = new BufferedReader(new InputStreamReader((java.io.InputStream) inputStream));

        try {
            String s = "";
            String element_to_find1 = element_to_find;
            String answer = "";
            while((s = br.readLine()) != null) {
                String[] values = s.split(",");
                if( values[2].startsWith(element_to_find1) == true)
                {
                    txt.setText("Name: " +values[1]+"\n"+"Atomic Number: "+values[0]+"\n"+"Atomic Mass: "+values[3]+"\n"+"Period: "+values[7]+"\n"+"Group: "+values[8]+"\n"+"Phase: "+values[9]
                    +"\n"+"Melting Point: "+values[20]+"\n"+"Boiling Point: "+values[21]+"\n"+"Valency: "+values[26]);
                    break;
                }
                else
                {
                    continue;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
});

reset_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        vibe.vibrate(80);
        txt.setText("---");
    }
});


    }







}

