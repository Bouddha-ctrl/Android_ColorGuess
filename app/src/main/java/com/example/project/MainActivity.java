package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioGroup r1;

    RadioGroup r2;
    Button btn;
    Button btn2;
    EditText et1;
    Spinner spinnerColor;
    CheckBox c1,c2,c3;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.valide);
        btn2 = findViewById(R.id.reset);
        r1 = findViewById(R.id.gradio1);
        r2 = findViewById(R.id.gradio2);
        et1 = findViewById(R.id.colorscount);
        c1 = findViewById(R.id.check1);
        c2 = findViewById(R.id.check2);
        c3 = findViewById(R.id.check3);


        ArrayList<String> colors = new ArrayList<String>();
        colors.add("red");
        colors.add("blue");
        colors.add("green");

        //bottom nav
        nav = findViewById(R.id.bottomNavigationView);
        nav.setSelectedItemId(R.id.page_3);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.page_1:
                        intent = new Intent(MainActivity.this, MainActivity2.class);

                        break;
                    case R.id.page_2:
                        intent = new Intent(MainActivity.this, MainActivity3.class);

                        break;
                    case R.id.page_3:
                        return true;

                }
                startActivity(intent);
                finish();
                return true;
            }
        });

        //spinner
        spinnerColor = findViewById(R.id.spinner_color);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,
                colors);
        spinnerColor.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score=0;



                //Question 1
                String qs1="";
                int id = r1.getCheckedRadioButtonId();
                if (id!=-1){
                    RadioButton rb  = findViewById(id);
                    int i = r1.indexOfChild(rb);
                    qs1 =colors.get(i);
                    if (i==0){
                        score++;
                        rb.setButtonTintList(ColorStateList.valueOf(Color.GREEN));
                    }else{
                        rb.setButtonTintList(ColorStateList.valueOf(Color.RED));
                    }
                }

                //disable r1
                for(int i = 0; i < r1.getChildCount(); i++)
                {
                    ((RadioButton) r1.getChildAt(i)).setEnabled(false);
                    ((RadioButton) r1.getChildAt(i)).setTextColor(getResources().getColor(android.R.color.white));;
                }
                ////////////

                //Question 2
                String qs2= spinnerColor.getSelectedItem().toString();

                if (qs2.equals("blue")){
                    score++;

                }
                //disable spinnerColor
                spinnerColor.setEnabled(false);

                //////////////

                //Question 3

                int count = Integer.parseInt(et1.getText().toString());
                if (count==5)
                {
                    score++;
                    et1.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                }else
                {
                    et1.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                }
                //disable et1
                et1.setEnabled(false);
                et1.setTextColor(getResources().getColor(android.R.color.black)); //text color dont change  after disable
                /////////////////


                //Question 4
                boolean checked1 = c1.isChecked();
                boolean checked2 = c2.isChecked();
                boolean checked3 = c3.isChecked();

                if (checked1 && checked2 && !checked3){
                    score++;
                }
                if (checked1) c1.setButtonTintList(ColorStateList.valueOf(Color.GREEN));
                if (checked2) c2.setButtonTintList(ColorStateList.valueOf(Color.GREEN));
                if (checked3) c3.setButtonTintList(ColorStateList.valueOf(Color.RED));

                //disable checkboxs
                c1.setEnabled(false);
                c2.setEnabled(false);
                c3.setEnabled(false);


                //Question 5
                String qs5="";
                int id2 = r2.getCheckedRadioButtonId();
                if (id2!=-1){
                    RadioButton rb2  = findViewById(id2);
                    int i = r2.indexOfChild(rb2);

                    if (i==2){
                        score++;
                        rb2.setButtonTintList(ColorStateList.valueOf(Color.GREEN));
                    }else{
                        rb2.setButtonTintList(ColorStateList.valueOf(Color.RED));
                    }
                }

                //disable r2
                for(int i = 1; i < r2.getChildCount(); i++)
                {
                    ((RadioButton) r2.getChildAt(i)).setEnabled(false);
                    ((RadioButton) r2.getChildAt(i)).setTextColor(getResources().getColor(android.R.color.black));
                }
                ////////////


                //Set button visibility
                btn.setVisibility(View.GONE);
                btn2.setVisibility(View.VISIBLE);
                /////
                ShowPopUp(v,score);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = getIntent();
                //t.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                finish();
                startActivity(t);

            }
        });
    }


    private void ShowPopUp(View v,int score){

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //set message
        ((TextView)popupWindow.getContentView().findViewById(R.id.textV)).setText("Your score : "+score*20+"/100");

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}