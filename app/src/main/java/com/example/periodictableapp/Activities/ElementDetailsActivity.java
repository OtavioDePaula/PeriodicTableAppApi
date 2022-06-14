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
    TextView txtAtomicMass;
    Element element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);

        Intent intent = getIntent();
        int atomicNumber = intent.getIntExtra("atomicNumber", 0);
        element = new Element();
        txtAtomicMass = findViewById(R.id.txtElementResult_AtomicMass);


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

                txtAtomicMass.setText(object.getString("atomicMass"));

                element.setAtomicNumber(object.getInt("atomicNumber"));
                element.setSymbol(object.getString("symbol"));
                element.setName(object.getString("name"));
                element.setAtomicMass(object.getString("atomicMass"));
                element.setElectronicConfiguration(object.getString("eletronicConfiguration"));
                element.setElectronegativity(object.getDouble("electronegativity"));
                element.setAtomicRadius(object.getInt("atomicRadius"));
                element.setIonRadius(object.getString("ionRadius"));
                element.setVanDerWaalsRadius(object.getInt("vanDerWaalsRadius"));
                element.setIonizationEnergy(object.getInt("ionizationEnergy"));
                element.setElectronAffinity(object.getInt("electronAffinity"));
                element.setGroupBlock(object.getString("groupBlock"));
                element.setOxidationStates(object.getString("oxidationStates"));
                element.setStandardState(object.getString("standardState"));
                element.setBondingType(object.getString("bondingType"));
                element.setMeltingPoint(object.getInt("meltingPoint"));
                element.setBoilingPoint(object.getInt("boilingPoint"));
                element.setDensity(object.getInt("density"));
                element.setGroupBlock(object.getString("groupBlock"));
                element.setYearDiscovered(object.getString("yearDiscovered"));
                element.setBlock(object.getString("block"));
                element.setCpkHexColor(object.getString("cpkHexColor"));
                element.setPeriod(object.getInt("period"));
                element.setGroup(object.getInt("group"));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}