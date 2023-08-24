package com.example.volleydemoimages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Data_Model> dataModalList = new ArrayList<Data_Model>();
    Data_Model dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String urlweb = "https://jsonplaceholder.typicode.com/photos";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlweb,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject mainobj = jsonArray.getJSONObject(i);

                                int id = mainobj.getInt("id");
                                String title = mainobj.getString("title");
                                String url = mainobj.getString("url");


                                dataModel = new Data_Model(id,title, url);
                                dataModalList.add(dataModel);

                            }
                            Log.d("YYY", "list of data" + dataModalList);
                            RecyclerAdapter adapter = new RecyclerAdapter(MainActivity.this,dataModalList);
                            recyclerView.setAdapter(adapter);

                            LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("YYY","onErrorResponse"+error.getLocalizedMessage());
                    }

                });
        queue.add(stringRequest);
    }
}