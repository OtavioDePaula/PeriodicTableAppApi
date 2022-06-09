package com.example.periodictableapp.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;

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

public class ElementsList extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    ListView listViewElements;
    List<Element> elementList;
    String quey, queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements_list);

        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
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
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new LoadElements(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        LoadElements load = new LoadElements(getApplicationContext(), "");
        quey = load.loadInBackground();

        try {
            JSONObject obj = new JSONObject(quey);
            JSONArray jsonArray = obj.getJSONArray("");
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                Element element = new Element();
                element.setSymbol(object.getString("symbol"));
                element.setGroupBlock(object.getString("groupblock"));
                element.setName(object.getString("name"));
                elementList.add(element);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listViewElements = (ListView) findViewById(R.id.listView_elementsList);

        ListViewElementsAdapter adapter = new ListViewElementsAdapter(this, R.layout.element_item, elementList);
        listViewElements.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}