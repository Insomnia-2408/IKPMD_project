package com.hsleiden.ikpmd_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hsleiden.ikpmd_project.Helpers.DatabaseHelper;
import com.hsleiden.ikpmd_project.Helpers.DatabaseInfo;
import com.hsleiden.ikpmd_project.Helpers.ListHelper;
import com.hsleiden.ikpmd_project.Helpers.MapHelper;
import com.hsleiden.ikpmd_project.Models.Route;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setClickListener();
        loadData();

    }

    private void setClickListener() {
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, RouteActivity.class), 1);
            }
        });

    }

    public void loadData() {

        ListView routesList = findViewById(R.id.routeList);
        routesList.setPadding(30, 0, 30, 0);

        routesList.setVisibility(View.VISIBLE);

        DatabaseHelper db = DatabaseHelper.getHelper(this);

        ListHelper helper = new ListHelper(this, this, db.getRoutesFromDB(), R.layout.layout);

        routesList.setAdapter(helper);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loadData();

    }
}
