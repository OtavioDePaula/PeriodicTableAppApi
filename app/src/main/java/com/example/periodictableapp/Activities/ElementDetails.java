package com.example.periodictableapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.periodictableapp.Element;
import com.example.periodictableapp.R;

public class ElementDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);

        Intent intent = getIntent();
        Element projeto = ((Element) intent.getSerializableExtra("Element"));
    }
}