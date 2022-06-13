package com.example.periodictableapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.periodictableapp.Element;
import com.example.periodictableapp.R;

public class ElementDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);

        Intent intent = getIntent();
        Element element = ((Element) intent.getSerializableExtra("Element"));

        Toast.makeText(ElementDetailsActivity.this, element.getName(), Toast.LENGTH_SHORT).show();
    }
}