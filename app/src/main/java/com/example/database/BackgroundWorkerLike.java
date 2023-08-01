package com.example.database;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorkerLike  extends AsyncTask<String,Void,String> {
    Context context;

    BackgroundWorkerLike(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String addComment_url = "http://10.0.2.2/NewsApp/addLike.php";
        try {
            String user_id = params[0];
            String article_id = String.valueOf(params[1]); // Convert integer to string
            String is_liked = String.valueOf(params[2]); // Convert integer to string

            URL url = new URL(addComment_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data =
                    URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8") + "&"
                            + URLEncoder.encode("article_id", "UTF-8") + "=" + URLEncoder.encode(article_id, "UTF-8") + "&"
                            + URLEncoder.encode("is_liked", "UTF-8") + "=" + URLEncoder.encode(is_liked, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
