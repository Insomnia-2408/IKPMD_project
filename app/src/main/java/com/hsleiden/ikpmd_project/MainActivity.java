package com.hsleiden.ikpmd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hsleiden.ikpmd_project.Helpers.MapHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeToast();
        setClickListener();
        MapHelper.initializeMaps();

    }

    private void makeToast() {
        Toast.makeText(this, "Hallo!", Toast.LENGTH_SHORT).show();
    }

    private void setClickListener() {
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RouteActivity.class));
            }
        });
    }

}
