package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        nav = findViewById(R.id.bottomNavigationView);

        nav.setSelectedItemId(R.id.page_1);


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.page_1:

                        return true;
                    case R.id.page_2:
                        intent = new Intent(MainActivity2.this, MainActivity3.class);

                        break;
                    case R.id.page_3:
                        intent = new Intent(MainActivity2.this, MainActivity.class);

                        break;
                }
                startActivity(intent);
                finish();
                return true;
            }
        });


    }
}