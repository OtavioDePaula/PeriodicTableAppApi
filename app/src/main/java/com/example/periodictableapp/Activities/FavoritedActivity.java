package com.example.periodictableapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.periodictableapp.Adapters.ListViewElementsAdapter;
import com.example.periodictableapp.Element;
import com.example.periodictableapp.Groupblock;
import com.example.periodictableapp.LoadElements;
import com.example.periodictableapp.R;
import com.example.periodictableapp.StandardState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FavoritedActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    ListView listViewElements;
    List<Element> elementList;
    String queryString;
    TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorited);

        loading = findViewById(R.id.teste);
        elementList = new ArrayList<Element>();

        queryString = "element/favorited/";

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
        loading.setVisibility(View.INVISIBLE);
        try {
            JSONArray jsonArrayElement = new JSONArray(data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArrayGroupBlock = jsonObject.getJSONArray("groupBlock");
            JSONArray jsonArrayStandardState = jsonObject.getJSONArray("standardState");



            for(int i = 0; i < jsonArrayStandardState.length(); i++)
            {

                JSONObject object = jsonArrayElement.getJSONObject(i);

                Groupblock groupBlock = new Groupblock();
                groupBlock.setgroupblockID(object.getInt("groupblockid"));
                groupBlock.setgroupblock(object.getString("groupblock"));

                StandardState standardState = new StandardState();
                standardState.setstandardStateID(object.getInt("standardstateid"));
                standardState.setstandardState(object.getString("standardstate"));

                Element element = new Element();
                element.setatomicNumber(object.getInt("atomicNumber"));
                element.setsymbol(object.getString("symbol"));
                element.setname(object.getString("name"));
                element.set_groupblock(groupBlock);
                element.set_standardState(standardState);
                elementList.add(element);
            }

            ListViewElementsAdapter adapter = new ListViewElementsAdapter(this, R.layout.element_item, elementList);
            listViewElements.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void openDelivery(View v) {
        Intent intent = new Intent(getApplicationContext(), DeliverySetupActivity.class);
        startActivity(intent);
    }

}