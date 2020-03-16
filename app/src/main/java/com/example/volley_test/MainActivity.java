package com.example.volley_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<NoteModel> noteModelArrayList;
    String URL_NOTE = "https://jsonplaceholder.typicode.com/todos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        addEvent();
    }

    private void addEvent() {
        getAllNote();
        noteAdapter = new NoteAdapter(MainActivity.this, noteModelArrayList);
        recyclerView.setAdapter(noteAdapter);
    }

    private void getAllNote() {
        final ProgressDialog loading = new ProgressDialog(MainActivity.this);
        loading.setMessage("Loading...");
        loading.show();
        //lay du lieu
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_NOTE, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++){
                                JSONObject item = response.getJSONObject(i);
                                NoteModel noteModel = new NoteModel();
                                noteModel.setUserId(item.getInt("userId"));
                                noteModel.setId(item.getInt("id"));
                                noteModel.setTitle(item.getString("title"));
                                noteModel.setCompleted(item.getBoolean("completed"));

                                noteModelArrayList.add(noteModel);
                            }
                            noteAdapter.notifyDataSetChanged();
                        }catch (Exception e){

                        }
                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void AnhXa() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        noteModelArrayList = new ArrayList<>();
    }
}
