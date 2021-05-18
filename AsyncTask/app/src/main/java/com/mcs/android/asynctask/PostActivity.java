package com.mcs.android.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcs.android.asynctask.adapters.CommentsRecyclerViewAdapter;
import com.mcs.android.asynctask.models.Comment;
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

import static com.mcs.android.asynctask.BaseApplication.CHANNEL_POST_ID;

public class PostActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    private TextView postTitle;
    private TextView postAuthor;
    private TextView postBody;

    private RecyclerView commentsRecyclerView;
    private CommentsRecyclerViewAdapter commentsRecyclerViewAdapter;
    ProgressDialog progressDialog;

    Post currentPost;
    ArrayList<Comment> commentsList = new ArrayList<Comment>();
    private SharedPreferences sharedPreferences;

    private class CommentLoaderTask extends AsyncTask<String, Void, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(PostActivity.this);
            progressDialog.setMessage("Loading comments...");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... urls) {
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
                Log.i("StringCommentResult", stringResult);
                // Now that the data is loaded from the url
                // The data needs to be parsed using Simple JSON by Google
                JSONParser jsonParser = new JSONParser();
                jsonArrayResult = (org.json.simple.JSONArray) jsonParser.parse(stringResult);

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

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                long postId = (long) jsonObject.get("postId");
                long id = (long) jsonObject.get("id");
                String authorName = (String) jsonObject.get("name");
                String authorEmail = (String) jsonObject.get("email");
                String body = (String) jsonObject.get("body");

                Comment comment = new Comment(postId, id, authorName, authorEmail, body.replace("\n", " "));
                commentsList.add(comment);
            }

            if (jsonArray.size() > 0)
                commentsRecyclerViewAdapter.notifyDataSetChanged();

            progressDialog.hide();

            Intent intent = new Intent(PostActivity.this, PostActivity.class);
            intent.putExtra("post", currentPost);
            PendingIntent pendingIntent = PendingIntent.getActivity(PostActivity.this, 0, intent, 0);

            Notification notification = new NotificationCompat.Builder(PostActivity.this, CHANNEL_POST_ID)
                    .setSmallIcon(R.drawable.ic_post)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_post))
                    .setContentTitle(currentPost.getTitle().toUpperCase())
                    .setContentText(currentPost.getBody())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .build();

            notificationManager.notify(1, notification);
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
        setContentView(R.layout.activity_post);

        Bundle bundle = getIntent().getExtras();
        currentPost = bundle.getParcelable("post");

        commentsRecyclerView = (RecyclerView) findViewById(R.id.commentsRecyclerView);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        commentsRecyclerViewAdapter = new CommentsRecyclerViewAdapter(this, commentsList);
        commentsRecyclerView.setAdapter(commentsRecyclerViewAdapter);

        postTitle = (TextView) findViewById(R.id.postTitle);
        postAuthor = (TextView) findViewById(R.id.postAuthor);
        postBody = (TextView) findViewById(R.id.postBody);

        LinearLayout linearLayout = (LinearLayout) postBody.getParent();
        linearLayout.setClickable(false);
        linearLayout.setFocusable(false);
        linearLayout.setForeground(null);

        postTitle.setText(currentPost.getTitle());
        postAuthor.setText("User " + currentPost.getUserId());
        postBody.setText(currentPost.getBody());


        notificationManager = NotificationManagerCompat.from(this);

        CommentLoaderTask commentLoaderTask = new CommentLoaderTask();
        commentLoaderTask.execute("https://jsonplaceholder.typicode.com/posts/" + currentPost.getId() + "/comments");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        recreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }
}