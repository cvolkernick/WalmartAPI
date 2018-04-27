package com.example.admin.walmartapi.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.walmartapi.R;
import com.example.admin.walmartapi.view.adapters.RecyclerViewAdapter;
import com.example.admin.walmartapi.view.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    MainPresenter mainPresenter;
    private RecyclerView rvMain;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;
    private List<Item> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rvMain);
        layoutManager = new LinearLayoutManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mainPresenter = MainPresenter.getDefault();
        mainPresenter.attachView(this);
        mainPresenter.getItems();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetItems(JSONArray items) {

        for (int i = 0; i < items.length(); i++) {
            try {


                JSONObject o = items.getJSONObject(i);
                Item item = new Item(
                        o.getString("name"),
                        Double.parseDouble(o.getString("salePrice")),
                        o.getString("shortDescription"),
                        o.getString("thumbnailImage")
                );

                itemsList.add(item);
                Log.d(TAG, "onGetItems: " + item.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        adapter = new RecyclerViewAdapter(itemsList);
        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(layoutManager);
    }
}
