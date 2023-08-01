package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class AdminDashboard extends AppCompatActivity implements MyAdapter3.OnDeleteClickListener {

    private static final String URL_DATA = "http://10.0.2.2/NewsApp/api.php";
    private RecyclerView recyclerView;
    private MyAdapter3 adapter;
    private List<ListItem> listItems;
    private Integer article_id;

    private ProgressDialog progressDialog;
    TextView username_view;
    ImageView profile_image;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        username_view = findViewById(R.id.username);
        //profile_image =  findViewById(R.id.profile_image);
        logoutBtn = findViewById(R.id.logoutBtn);

        listItems = new ArrayList<>();
        adapter = new MyAdapter3(AdminDashboard.this, listItems, this);
        recyclerView.setAdapter(adapter); // Set the adapter first
        loadRecyclerViewData();
        Intent intent = getIntent();


        if (intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");

            // Set the username to the TextView
            username_view.setText("welcome "+username+"!");

        }



        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onLogout();
            }
        });


    }
    private void onLogout() {

        Intent intent = new Intent(AdminDashboard.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void loadRecyclerViewData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data..");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray posts = new JSONArray(response);
                            for(int i=0; i<posts.length();i++){
                                JSONObject postsObject = posts.getJSONObject(i);
                                 article_id =postsObject.getInt("id");
                                String title =postsObject.getString("title");
                                String category =postsObject.getString("category");
                                String date =postsObject.getString("date");
                                String content =postsObject.getString("content");
                                String src =postsObject.getString("src");
                                String image =postsObject.getString("image");

                                ListItem post = new ListItem (article_id,title,category,date,content,src,image);
                                listItems.add(post);
                            }
                            recyclerView.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminDashboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
    @Override
    public void onDeleteClick(int position) {
        Toast.makeText(this, "button clicked!", Toast.LENGTH_SHORT).show();

        ListItem itemToDelete = listItems.get(position);

        String deleteUrl = "http://10.0.2.2/newsapp/deletepost.php?id=" +  itemToDelete.getArticle_id();
        String articleIdString = String.valueOf(itemToDelete.getArticle_id());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, deleteUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //Log.d("response", response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            String message = jsonObject.getString("message");

                            if (success) {

                                listItems.remove(position);
                                recyclerView.getAdapter().notifyItemRemoved(position);
                                recyclerView.getAdapter().notifyItemRangeChanged(position, listItems.size());

                                Toast.makeText(AdminDashboard.this, message, Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(AdminDashboard.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //
                            Toast.makeText(AdminDashboard.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error handling for network request
                        Toast.makeText(AdminDashboard.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(this).add(stringRequest);

    }


}