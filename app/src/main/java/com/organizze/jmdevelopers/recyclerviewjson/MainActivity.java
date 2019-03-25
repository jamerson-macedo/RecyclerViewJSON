package com.organizze.jmdevelopers.recyclerviewjson;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ArrayList<Item> itemArrayList;
    private ItemAdapter itemAdapter;
    private RequestQueue requestQueue;
    public static final String EXTRA_FOTO="imagemURL";
    public static final String EXTRA_CRIADOR="criador";
    public static final String EXTRA_DOWNLOAD="downloads";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemArrayList=new ArrayList<>();
        Context context;
        requestQueue=Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        // pega a url
        String URL="https://pixabay.com/api/?key=11982729-98e22b893c204e6f42460c30b&q=yellow+flowers&image_type=photo";
        // o bjeto json como metodo get

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // pegabdo o array do json
                    JSONArray jsonArray=response.getJSONArray( "hits");
                    for (int i =0;i<jsonArray.length(); i++){
                        JSONObject jsonHITS=jsonArray.getJSONObject(i);
                        String criador=jsonHITS.getString("user");
                        String imagemref=jsonHITS.getString("webformatURL");
                        int downloads=jsonHITS.getInt("likes");
                        // passando os dados para o arraylist
                        itemArrayList.add(new Item(imagemref,criador,downloads));


                    }
                    // estanciando o adapter e setando no recycler view
                    itemAdapter=new ItemAdapter(MainActivity.this,itemArrayList);
                    recyclerView.setAdapter(itemAdapter);
                    itemAdapter.setOnItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        });
       // passando o object para o request
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(this,DetalhesActivity.class);
        Item clicado=itemArrayList.get(position);
        intent.putExtra(EXTRA_FOTO,clicado.getmImagemURL());
        intent.putExtra(EXTRA_CRIADOR,clicado.getmCriador());
        intent.putExtra(EXTRA_DOWNLOAD,clicado.getmLikes());
        startActivity(intent);

    }
}
