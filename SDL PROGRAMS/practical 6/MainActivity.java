package com.animation;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Button save,read;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input=(EditText)findViewById(R.id.input);
        res=(TextView)findViewById(R.id.res);
        save=(Button)findViewById(R.id.save);
        read=(Button)findViewById(R.id.read);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToFile(input.getText().toString());
                input.setText("");
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile(new File(Environment.getExternalStorageDirectory()+"//memoryFile.txt"));
            }
        });
    }
    void writeToFile(String data)
    {
        try {
            PrintWriter writer = new PrintWriter(Environment.getExternalStorageDirectory()+"//memoryFile.txt", "UTF-8");
            writer.println(data);
            writer.close();
            Toast.makeText(this,"file written", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    void readFromFile(File file)
    {   try {
        String buffer1;
        StringBuilder text = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append('\n');
        }
        br.close();
        buffer1 = text.toString();
        res.setText(buffer1);
    }
    catch (Exception e){
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
    }


}
