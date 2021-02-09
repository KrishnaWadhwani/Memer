package com.memer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    public String urlmeme;
    public void next(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://meme-api.herokuapp.com/gimme";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject obj = new JSONObject(
                                    response
                            );

                            urlmeme = obj.getString("url");

                            ImageView memeimg = findViewById(R.id.meme);

                            Picasso.get().load(urlmeme).into(memeimg);

                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Some Error Occurred In Loading Memes", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Some Error Occurred Please Try Again Later", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://meme-api.herokuapp.com/gimme";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject obj = new JSONObject(
                                    response
                            );

                            urlmeme = obj.getString("url");
                            ImageView memeimg = findViewById(R.id.meme);
                            Picasso.get().load(urlmeme).into(memeimg);
                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Some Error Occurred In Loading Memes", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Some Error Occurred Please Try Again Later", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    public Object share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey Buddy See This Nice Meme From Reddit "+urlmeme);
        sendIntent.setType("text/*");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        return urlmeme;
    }
    private class Memes {

        @SerializedName("postLink")
        @Expose
        private String postLink;
        @SerializedName("subreddit")
        @Expose
        private String subreddit;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("nsfw")
        @Expose
        private Boolean nsfw;
        @SerializedName("spoiler")
        @Expose
        private Boolean spoiler;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("ups")
        @Expose
        private Integer ups;
        @SerializedName("preview")
        @Expose
        private List<String> preview = null;

        public String getPostLink() {
            return postLink;
        }

        public void setPostLink(String postLink) {
            this.postLink = postLink;
        }

        public String getSubreddit() {
            return subreddit;
        }

        public void setSubreddit(String subreddit) {
            this.subreddit = subreddit;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getNsfw() {
            return nsfw;
        }

        public void setNsfw(Boolean nsfw) {
            this.nsfw = nsfw;
        }

        public Boolean getSpoiler() {
            return spoiler;
        }

        public void setSpoiler(Boolean spoiler) {
            this.spoiler = spoiler;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Integer getUps() {
            return ups;
        }

        public void setUps(Integer ups) {
            this.ups = ups;
        }

        public List<String> getPreview() {
            return preview;
        }

        public void setPreview(List<String> preview) {
            this.preview = preview;
        }


    }
}