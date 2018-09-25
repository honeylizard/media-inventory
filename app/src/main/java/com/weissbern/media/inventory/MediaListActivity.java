package com.weissbern.media.inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * class MediaListActivity
 */
public class MediaListActivity extends AppCompatActivity {

    RecyclerView mediaListView;
    MediaAdapter mediaAdapter;

    List<Media> mediaList;

    String criteria_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        // Initialize the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get the data from the form submission
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            criteria_title = extras.getString("FILTER_TITLE");
        }
        Log.d("FILTER_TITLE", criteria_title);

        // Populate the list of media
        mediaList = new ArrayList<>();

        mediaListView = findViewById(R.id.media_list_view);
        mediaListView.setLayoutManager(new LinearLayoutManager(this));
        mediaListView.setHasFixedSize(true);

        populateMediaList();

        mediaAdapter = new MediaAdapter(this, mediaList);
        mediaListView.setAdapter(mediaAdapter);

        // Generate a popup
        Toast myToast = Toast.makeText(
                getApplicationContext(),
                R.string.filter_submitted,
                Toast.LENGTH_LONG
        );
        myToast.show();

    }

    /**
     * Gets a local JSON file and returns its contents.
     *
     * @return String
     */
    private String loadAssetJSON() {
        String json;
        try {
            InputStream input = this.getAssets().open("example_data.json");
            int size = input.available();
            byte[] buffer = new byte[size];
            if (input.read(buffer) == -1) {
                throw new EOFException();
            }
            input.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * Reads a JSON string and parses the information to populate the list of media.
     */
    private void populateMediaList() {
        // Open and parse file
        try {
            //TODO change this to get a JSON response from the Google Sheet
            JSONObject json_obj = new JSONObject(loadAssetJSON());

            //TODO adjust parsing of the JSON response array as needed
            JSONArray json_arr = json_obj.getJSONArray("media");

            for (int index = 0; index < json_arr.length(); index++) {
                JSONObject jo_inside = json_arr.getJSONObject(index);

                String title = jo_inside.getString("title");
                String notes = jo_inside.getString("notes");

                int media_type_drawable = Media.getMediaTypeDrawable(jo_inside);
                String media_subtypes = Media.getSubtypesList(jo_inside);

                mediaList.add(new Media(index, title, media_subtypes, notes, media_type_drawable));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
