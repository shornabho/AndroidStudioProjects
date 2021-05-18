package com.mcs.android.asynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mcs.android.asynctask.adapters.PostsRecyclerViewAdapter;
import com.mcs.android.asynctask.models.Post;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView postsRecyclerView;
    private PostsRecyclerViewAdapter postsRecyclerViewAdapter;
    private ArrayList<Post> postsList = new ArrayList<Post>();
    private ProgressDialog progressDialog;

    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private int currentUserId = 1;
    boolean isLoading = false;
    private SharedPreferences sharedPreferences;


    private class PostLoaderTask extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // To show loading
            if (postsList.size() < 100) {
                postsList.add(null);
                postsRecyclerViewAdapter.notifyDataSetChanged();
                isLoading = true;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected JSONArray doInBackground(String... urls) {
            if (postsList.size() >= 100) {
                return null;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String stringResult = "";
            JSONArray jsonArrayResult = new JSONArray();
            URL url;
            HttpURLConnection connection;
            try {
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1) {
                    char current = (char) data;
                    stringResult += current;
                    data = inputStreamReader.read();
                }
                Log.i("StringResult", stringResult);
                // Now that the data is loaded from the url
                // The data needs to be parsed using Simple JSON by Google
                JSONParser jsonParser = new JSONParser();
                jsonArrayResult = (JSONArray) jsonParser.parse(stringResult);

            } catch (MalformedURLException e) {
                Log.i("CaughtException", "Malformed URL Exception");
                e.printStackTrace();
            } catch (IOException e) {
                Log.i("CaughtException", "IO Exception");
                e.printStackTrace();
            } catch (ParseException e) {
                Log.i("CaughtException", "Parse Exception");
                e.printStackTrace();
            }

            return jsonArrayResult;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            if (jsonArray == null)
                return;

            int nullElementIndex = postsList.size() - 1;



            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                long postId = (long) jsonObject.get("id");
                long postAuthorUserId = (long) jsonObject.get("userId");
                String postTitle = (String) jsonObject.get("title");
                String postBody = (String) jsonObject.get("body");

                Post post = new Post(postId, postAuthorUserId, postTitle, postBody.replace("\n", " "));
                postsList.add(post);
            }

            // Stop showing loading
            postsList.remove(nullElementIndex);
            isLoading = false;

            postsRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.mcs.android.asynctask.settings", MODE_PRIVATE);
        boolean darkMode = sharedPreferences.getBoolean("darkMode", false);
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main);
        postsRecyclerView = (RecyclerView) findViewById(R.id.postsRecyclerView);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        postsRecyclerViewAdapter = new PostsRecyclerViewAdapter(this, postsList);
        postsRecyclerView.setAdapter(postsRecyclerViewAdapter);

        loadMoreData();
        initScrollListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    private void loadMoreData() {
        PostLoaderTask postLoaderTask = new PostLoaderTask();
        postLoaderTask.execute(POSTS_URL + "?userId=" + currentUserId++);
    }

    private void initScrollListener() {
        postsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == postsList.size() - 1) {
                        loadMoreData();
                        isLoading = true;
                    }
                }
            }
        });
    }
}