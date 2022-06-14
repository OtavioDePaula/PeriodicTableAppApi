package com.example.periodictableapp.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.periodictableapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DeliverySetupActivity extends AppCompatActivity {
    EditText txtBairro,
            txtCidade,
            txtCEP,
            txtLogradouro,
            txtNumeroEndereco,
            txtUF;

    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_setup);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        LinearLayout btnCurrentLocation = (LinearLayout) findViewById(R.id.btnCurrentLocation);
        txtBairro = (EditText) findViewById(R.id.DeliverySetup_inputDistricts);
        txtCidade = (EditText) findViewById(R.id.DeliverySetup_inputCity);
        txtCEP = (EditText) findViewById(R.id.DeliverySetup_inputPostalCode);
        txtLogradouro = (EditText) findViewById(R.id.DeliverySetup_inputAddressLine);
        txtNumeroEndereco = (EditText) findViewById(R.id.DeliverySetup_inputNumber);
        txtUF = (EditText) findViewById(R.id.DeliverySetup_inputStateProvince);

        btnCurrentLocation.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //PERMISSÃO CEDIDA
                getCurrentLocation();
            } else {
                //PERMISSÃO NEGADA
                ActivityCompat.requestPermissions(DeliverySetupActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        44);
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            //PEGAR A LOCALIZAÇÃO
            Location location = task.getResult();
            if (location != null) {
                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addressList = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1
                    );
                    //EXIBIR TEXTOS
                    txtLogradouro.setText(Html.fromHtml(String.valueOf(addressList.get(0).getThoroughfare())));
                    txtNumeroEndereco.setText(Html.fromHtml(String.valueOf(addressList.get(0).getFeatureName())));
                    txtCEP.setText((Html.fromHtml(String.valueOf(addressList.get(0).getPostalCode()))));
                    txtCidade.setText(Html.fromHtml(String.valueOf(addressList.get(0).getSubAdminArea())));
                    txtUF.setText(Html.fromHtml(String.valueOf(addressList.get(0).getAdminArea())));
                    txtBairro.setText(Html.fromHtml(String.valueOf(addressList.get(0).getSubLocality())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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