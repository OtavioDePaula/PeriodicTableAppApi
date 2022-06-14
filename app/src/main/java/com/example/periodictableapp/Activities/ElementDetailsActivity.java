package com.example.periodictableapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.periodictableapp.Adapters.ListViewElementsAdapter;
import com.example.periodictableapp.Element;
import com.example.periodictableapp.LoadElements;
import com.example.periodictableapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ElementDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    String queryString;
    TextView txtAtomicNumber,
            txtSymbol,
            txtName,
            txtAtomicMass,
            txtEletronicConfiguration,
            txtElectronegativity,
            txtAtomicRadius,
            txtIonRadius,
            txtVanderwaalsradius,
            txtIonizationEnergy,
            txtElectroaffinity,
            txtOxidationState,
            txtStandardState,
            txtBodingType,
            txtMeltingPoint,
            txtBoilingType,
            txtDensity,
            txtGroupBlock,
            txtYearDiscovered,
            txtBlock,
            txtPeriod,
            txtGroup;
    Element element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);

        Intent intent = getIntent();
        int atomicNumber = intent.getIntExtra("atomicNumber", 0);
        element = new Element();

        txtAtomicNumber = (TextView) findViewById(R.id.txtElementResult_AtomicNumber);
        txtSymbol = (TextView) findViewById(R.id.txtElementResult_Symbol);
        txtName = (TextView) findViewById(R.id.txtElementResult_Name);
        txtAtomicMass = (TextView) findViewById(R.id.txtElementResult_AtomicMass);
        txtEletronicConfiguration = (TextView) findViewById(R.id.txtElementResult_EletronicConfiguration);
        txtElectronegativity = (TextView) findViewById(R.id.txtElementResult_ElectronicNegativity);
        txtAtomicRadius = (TextView) findViewById(R.id.txtElementResult_AtomicRadius);
        txtIonRadius = (TextView) findViewById(R.id.txtElementResult_IonRadius);
        txtVanderwaalsradius = (TextView) findViewById(R.id.txtElementResult_vanderwaalsradius);
        txtIonizationEnergy = (TextView) findViewById(R.id.txtElementResult_ionizationenergy);
        txtElectroaffinity = (TextView) findViewById(R.id.txtElementResult_electroaffinity);
        txtGroupBlock = (TextView) findViewById(R.id.txtElementResult_groupblock);
        txtOxidationState = (TextView) findViewById(R.id.txtElementResult_oxidationstate);
        txtStandardState = (TextView) findViewById(R.id.txtElementResult_standardstate);
        txtBodingType = (TextView) findViewById(R.id.txtElementResult_bodingtype);
        txtMeltingPoint = (TextView) findViewById(R.id.txtElementResult_meltingpoint);
        txtBoilingType = (TextView) findViewById(R.id.txtElementResult_boilingtype);
        txtDensity = (TextView) findViewById(R.id.txtElementResult_density);
        txtGroupBlock = (TextView) findViewById(R.id.txtElementResult_groupblock);
        txtYearDiscovered = (TextView) findViewById(R.id.txtElementResult_yeardiscovered);
        txtBlock = (TextView) findViewById(R.id.txtElementResult_block);
        txtPeriod = (TextView) findViewById(R.id.txtElementResult_period);
        txtGroup = (TextView) findViewById(R.id.txtElementResult_group);
        

        // queryString = "element/atomicNumber/" + String.valueOf(element.getAtomicNumber());
        queryString = "element/atomicNumber/" + String.valueOf(atomicNumber);

        // Toast.makeText(ElementDetailsActivity.this, element.getName(), Toast.LENGTH_SHORT).show();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected())
        {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new LoadElements(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);

                try {
                    txtAtomicNumber.setText(String.valueOf(object.getInt("atomicNumber")));
                    txtSymbol.setText(object.getString("symbol"));
                    txtName.setText(object.getString("name"));
                    txtAtomicMass.setText(object.getString("atomicMass"));
                    txtEletronicConfiguration.setText(object.getString("electronicConfiguration"));
                    txtElectronegativity.setText(String.valueOf(object.getDouble("electronegativity")));
                    txtAtomicRadius.setText(String.valueOf(object.getInt("atomicRadius")));
                    txtIonRadius.setText(object.getString("ionRadius"));
                    txtVanderwaalsradius.setText(object.getString("vanDerWaalsRadius"));
                    txtIonizationEnergy.setText(String.valueOf(object.getInt("ionizationEnergy")));
                    txtElectroaffinity.setText(String.valueOf(object.getInt("electronAffinity")));
                    txtOxidationState.setText(object.getString("oxidationStates"));
                    txtStandardState.setText(object.getString("standardState"));
                    txtBodingType.setText(object.getString("bondingType"));
                    txtMeltingPoint.setText(String.valueOf(object.getInt("meltingPoint")));
                    txtBoilingType.setText(String.valueOf(object.getInt("boilingPoint")));
                    txtDensity.setText(String.valueOf(object.getDouble("density")));
                    txtGroupBlock.setText(object.getString("groupBlock"));
                    txtYearDiscovered.setText(object.getString("yearDiscovered"));
                    txtBlock.setText(object.getString("block"));
                    txtPeriod.setText(String.valueOf(object.getInt("period")));
                    txtGroup.setText(String.valueOf(object.getInt("group")));
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

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