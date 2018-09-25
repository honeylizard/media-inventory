package com.weissbern.media.inventory;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * class MainActivity
 */
public class MainActivity extends AppCompatActivity {

    int onStartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onStartCount = 1;
        if (savedInstanceState == null) {
            this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        } else {
            onStartCount = 2;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        } else if (onStartCount == 1) {
            onStartCount++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This will compile all of the form fields into a Bundle
     * sent to the MediaList Activity via an Intent.
     *
     * This Intent will also perform a sliding animation from the right side to the left.
     *
     * @param v The view that the button is on.
     */
    public void onSearch(View v) {
        // Initialize the Intent and Bundle for the next activity
        Intent intent = new Intent(v.getContext(), MediaListActivity.class);
        Bundle formBundle = new Bundle();

        // Prepare the Bundle of Form Fields
        //TODO Add more search criteria field values as needed
        formBundle.putString("FILTER_TITLE", getTitleFieldValue());
        intent.putExtras(formBundle);

        // Set the animation
        ActivityOptions options = ActivityOptions.makeCustomAnimation(
                v.getContext(), R.anim.slide_in_left, R.anim.slide_out_left
        );

        // Start the MediaList Activity and send the Bundle to it.
        startActivity(intent, options.toBundle());
    }

    /**
     * Gets the value from the Title text field.
     *
     * @return String
     */
    private String getTitleFieldValue() {
        // Get the Title text field
        TextInputLayout textFieldSet = findViewById(R.id.exampleTextField);
        EditText textField = textFieldSet.getEditText();

        String fieldValue = " ";
        if (textField != null) {
            fieldValue  += textField.getText().toString();
        }

        //TODO Add validation for the Title text field

        return fieldValue;
    }

}
