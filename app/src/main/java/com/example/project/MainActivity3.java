package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity3 extends AppCompatActivity {
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nav = findViewById(R.id.bottomNavigationView);

        nav.setSelectedItemId(R.id.page_2);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.page_1:
                        intent = new Intent(MainActivity3.this, MainActivity2.class);

                        break;
                    case R.id.page_2:

                        return true;
                    case R.id.page_3:
                        intent = new Intent(MainActivity3.this, MainActivity.class);

                        break;
                }
                startActivity(intent);
                finish();
                return true;
            }
        });
    }
}