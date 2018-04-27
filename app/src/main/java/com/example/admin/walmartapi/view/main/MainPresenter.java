package com.example.admin.walmartapi.view.main;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    private static MainPresenter instance;
    private RequestQueue queue;

    public static final String BASE_URL = "https://api.walmartlabs.com/v1/paginated/items?format=json";
    public static final String KEY_PARAM = "&apiKey=";
    public static final String API_KEY = "utra95z3dvqh8jv27mte3385";

    private MainPresenter() {}

    public static MainPresenter getDefault() {

        if (instance == null) {
            instance = new MainPresenter();
        }

        return instance;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public String buildUrl() {
        return BASE_URL + KEY_PARAM + API_KEY;
    }

    @Override
    public void getItems() {
        queue = Volley.newRequestQueue((Context)view);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                buildUrl(),
                null,
                new APIResponse(),
                new APIResponse()
        );

        queue.add(jsonObjectRequest);
    }

    public class APIResponse implements Response.Listener<JSONObject>, Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText((Context)view, error.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(JSONObject response) {
            try {
                view.onGetItems(response.getJSONArray("items"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
