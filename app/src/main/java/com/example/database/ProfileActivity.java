package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://10.0.2.2/NewsApp/api.php";
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<ListItem> listItems;

   // private String  userUrl = "http://10.0.2.2/NewsApp/fetchUsers.php";

    private ProgressDialog progressDialog;
    private String user_id,image_url;
    TextView username_view;
    ImageView profile_image;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        username_view = findViewById(R.id.username);
        profile_image =  findViewById(R.id.profile_image);
        logoutBtn = findViewById(R.id.logoutBtn);

        listItems = new ArrayList<>();

        loadRecyclerViewData();


        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        image_url = intent.getStringExtra("img_url");
        Glide.with(this)
                .load(image_url)
                .into(profile_image);

        if (intent.hasExtra("username")) {

            String username = intent.getStringExtra("username");


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

        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
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
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                          JSONArray posts = new JSONArray(response);
                          for(int i=0; i<posts.length();i++){

                            JSONObject postsObject = posts.getJSONObject(i);
                            Integer article_id =postsObject.getInt("id");
                            String title =postsObject.getString("title");
                            String category =postsObject.getString("category");
                            String date =postsObject.getString("date");
                            String content =postsObject.getString("content");
                            String src =postsObject.getString("src");
                            String image =postsObject.getString("image");

                            ListItem post = new ListItem (article_id,title,category,date,content,src,image);
                              listItems.add(post);
                          }
                          adapter = new MyAdapter(ProfileActivity.this,listItems,user_id);
                          recyclerView.setAdapter(adapter);
                          adapter.notifyDataSetChanged();

                        }
                        catch (JSONException e) {
                          e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
       Volley.newRequestQueue(this).add(stringRequest);
    }


}