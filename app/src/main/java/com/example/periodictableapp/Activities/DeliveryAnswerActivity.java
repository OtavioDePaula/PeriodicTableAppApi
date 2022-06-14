package com.example.periodictableapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.periodictableapp.R;

public class DeliveryAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_answer);
    }

    // MENU
    public void openHome(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void openList(View v) {
        Intent intent = new Intent(getApplicationContext(), ElementsListActivity.class);
        startActivity(intent);
    }

    public void openFavorites(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void openDelivery(View v) {
        Intent intent = new Intent(getApplicationContext(), DeliverySetupActivity.class);
        startActivity(intent);
    }
}