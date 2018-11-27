
package com.example.mylenovo.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    // Class to show all the information/details of an entry

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve the information (on entrie) saved in intent
        Intent intent = getIntent();
        String title = (String) intent.getSerializableExtra("title");
        String content = (String) intent.getSerializableExtra("content");
        String mood = (String) intent.getSerializableExtra("mood");
        String date = (String) intent.getSerializableExtra("date");

        TextView tv_title = this.findViewById(R.id.tv_titel);
        TextView tv_mood = this.findViewById(R.id.tv_mood);
        TextView tv_content = this.findViewById(R.id.tv_text);
        TextView tv_date = findViewById(R.id.tv_datum);

        // Set information to textviews in activity_detail
        tv_title.setText(title);
        tv_mood.setText(mood);
        tv_content.setText(content);
        tv_date.setText(date);
    }

    // When back-button pressed, go back to MainActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
