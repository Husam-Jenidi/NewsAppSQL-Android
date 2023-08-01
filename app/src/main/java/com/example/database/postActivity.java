package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class postActivity extends AppCompatActivity {

    private TextView postContentTextView, Headline, Source, Date,numberOfLikes;
    private RecyclerView recyclerView;
    private MyAdapter2 adapter;
    private List<ListComment> listComments;
    private   List<listLike> listLikes;
    private EditText commentEditText;
    private Button submitCommentButton;
    private ImageView Cover,likeBtn;
    private String title,article_id, date,user_id,image_url;
    private int counter;
   String is_liked;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        postContentTextView = findViewById(R.id.postContentTextView);
        Headline = findViewById(R.id.postHeadline);
        Source = findViewById(R.id.source);
        Date = findViewById(R.id.date);
        Cover= findViewById(R.id.cover);
        likeBtn=findViewById(R.id.likeBtn);
        numberOfLikes= findViewById(R.id.counter);
        recyclerView = findViewById(R.id.commentRecycle);

        numberOfLikes.setText(Integer.toString(counter));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listComments = new ArrayList<>();
        adapter = new MyAdapter2(this, listComments);
        recyclerView.setAdapter(adapter);

        commentEditText =  findViewById(R.id.commentEditText);

        submitCommentButton =  findViewById(R.id.commentBtn);

        submitCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle the submission of the comment
                submitComment();
            }
        });
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLike();
            }
        });

        Intent intent = getIntent();

        if (intent != null) {
                    title = intent.getStringExtra("post_title");
                    String content = intent.getStringExtra("post_content");
                    String source = intent.getStringExtra("post_source");
                    date = intent.getStringExtra("post_date");
                    article_id = intent.getStringExtra("article_id");
                    user_id = intent.getStringExtra("user_id");
                    image_url = intent.getStringExtra("article_image");

                    // Set the title and content to the corresponding TextViews
                    Headline.setText(title);
                    postContentTextView.setText(content);
                    Source.setText(source);
                    Date.setText(date);
                    Glide.with(this)
                    .load(image_url)
                    .into(Cover);

                    // Enable scrolling for the post content TextView
                    postContentTextView.setMovementMethod(new ScrollingMovementMethod());

                    // Fetch comments data from the API
                    String apiUrl = "http://10.0.2.2/NewsApp/comment.php";
                   new GetCommentsRequest().execute(apiUrl);
                    adapter.notifyDataSetChanged();


                }

    }
    private void addLike() {

         is_liked = "yes";
        storeLikeInDataBase();
            adapter.notifyDataSetChanged();
            counter =counter+1;
            numberOfLikes.setText(Integer.toString(counter));
            Toast.makeText(this, "Like added!", Toast.LENGTH_SHORT).show();

        }

    private void storeLikeInDataBase() {

        BackgroundWorkerLike backgroundWorkerLike = new BackgroundWorkerLike(this);
        backgroundWorkerLike.execute(user_id,article_id,is_liked);
        adapter.notifyDataSetChanged();

    }





    private void submitComment() {

        // Get the comment text from the EditText
        String commentText = commentEditText.getText().toString().trim();

        // Check if the comment text is not empty
        if (!commentText.isEmpty()) {
            // Call a method to store the comment in the database
            storeCommentInDatabase(commentText);
            commentEditText.setText("");
            Toast.makeText(this, "Comment added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter your comment.", Toast.LENGTH_SHORT).show();
        }
    }
    private void storeCommentInDatabase(String commentText) {
        String apiUrl = "http://10.0.2.2/newsapp/addComment.php";
        String str_comment = commentEditText.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(postActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiUrl,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the JSON data and update the RecyclerView
                        try {
                            Log.d("response", response);
                            JSONObject commentObject = new JSONObject(response);
                            String user = commentObject.getString("name");
                            String comment = commentObject.getString("comment_text");
                            String title1 = commentObject.getString("title");
                            String image_url = commentObject.getString("img_url");
                            ListComment listComment = new ListComment(title1,user, comment, image_url);
                            listComments.add(0, listComment);
                            adapter.notifyItemInserted(0);

                        } catch (JSONException e) {
                           e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("comment_text", str_comment);
                params.put("user_id", user_id);
                params.put("article_id", article_id);

                return params;
            }
        };
        queue.add(stringRequest);

    }
    private class GetCommentsRequest extends AsyncTask<String, Void, String> {
        @Override
       protected String doInBackground(String... params) {
            String apiUrl = params[0];

            RequestQueue queue = Volley.newRequestQueue(postActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            parseCommentsData(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(postActivity.this, "Error fetching comments: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            queue.add(stringRequest);
            return null;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void parseCommentsData(String response) {
        try {
            JSONArray commentsArray = new JSONArray(response);
            listComments.clear();
            for (int i = 0; i < commentsArray.length(); i++) {
                JSONObject commentObject = commentsArray.getJSONObject(i);
                String user = commentObject.getString("name");
                String comment = commentObject.getString("comment_text");
                String title1 = commentObject.getString("title");
                String image_url = commentObject.getString("img_url");
                if(title1.equals(title)){

                    ListComment listComment = new ListComment(title1,user, comment, image_url);
                    listComments.add(listComment);
                    adapter.notifyDataSetChanged();

                }
            }

            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
