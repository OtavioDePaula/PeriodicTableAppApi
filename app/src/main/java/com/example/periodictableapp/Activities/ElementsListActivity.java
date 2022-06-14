package com.example.periodictableapp.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.periodictableapp.Adapters.ListViewElementsAdapter;
import com.example.periodictableapp.Element;
import com.example.periodictableapp.LoadElements;
import com.example.periodictableapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ElementsListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    ListView listViewElements;
    List<Element> elementList;
    String queryString;
    TextView teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements_list);

        teste = findViewById(R.id.teste);
        elementList = new ArrayList<Element>();
        listViewElements = (ListView) findViewById(R.id.listView_elementsList);

        queryString = "elements";

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
        teste.setVisibility(View.INVISIBLE);
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                Element element = new Element();
                element.setAtomicNumber(object.getInt("atomicNumber"));
                element.setSymbol(object.getString("symbol"));
                element.setGroupBlock(object.getString("groupBlock"));
                element.setName(object.getString("name"));
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
}