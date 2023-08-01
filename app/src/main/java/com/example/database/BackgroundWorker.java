    package com.example.database;

    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.Intent;
    import android.os.AsyncTask;
    import android.widget.Toast;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStream;
    import java.io.OutputStreamWriter;
    import java.net.HttpURLConnection;
    import java.net.MalformedURLException;
    import java.net.URL;
    import java.net.URLEncoder;

    public class BackgroundWorker extends AsyncTask<String,Void,String> {
        Context context;
        String username,id;
        AlertDialog alertDialog;
        BackgroundWorker(Context ctx,String username){
            context =ctx;
            this.username =username;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://10.0.2.2/NewsApp/login.php";
            String register_url = "http://10.0.2.2/NewsApp/register.php";

            if (type.equals("login")){
                try {
                    String user_name = params[1];
                    String password = params[2];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStreamWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "iso-8859-1");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder resultBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        resultBuilder.append(line);
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return resultBuilder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (type.equals("register")){
                try {
                    String name = params[1];
                    String email = params[2];
                    String password = params[3];


                    URL url = new URL (register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&" +"&"+
                    URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream= httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result = "";
                    String line = "";
                    while((line = bufferedReader.readLine()) != null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
        alertDialog= new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contains("Login not success")) {
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Login not success");
                alertDialog.show();
            } else {
                alertDialog.setMessage("Welcome!");
                alertDialog.show();

            try {
                    JSONObject jsonObject = new JSONObject(result);
                    String loginStatus = jsonObject.getString("login_status");
                    String id = jsonObject.getString("id");
                    String img_url= jsonObject.getString("img_url");
                if (loginStatus.equals("success") && jsonObject.getString("admin").equals("yes"))
                {
                        Intent intent = new Intent(context, AdminDashboard.class);
                        intent.putExtra("username", username);
                        intent.putExtra("user_id", id);
                       intent.putExtra("img_url",img_url);
                        context.startActivity(intent);
                    }
                else if (loginStatus.equals("success") && jsonObject.getString("admin").equals("no"))
                {
                        Intent intent = new Intent(context, ProfileActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("user_id", id);
                        intent.putExtra("img_url",img_url);

                        context.startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
