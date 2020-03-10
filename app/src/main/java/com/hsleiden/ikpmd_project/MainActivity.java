package com.hsleiden.ikpmd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeToast();
    }

    private void makeToast() {
        Toast.makeText(this, "Hallo!", Toast.LENGTH_SHORT).show();
    }
}
