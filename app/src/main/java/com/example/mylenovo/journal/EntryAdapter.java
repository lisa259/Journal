package com.example.mylenovo.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {
    // Class to create adapter to show entries on listview

    // Constructor
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    // add item in adapter to listview
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // get information from entry/cursor
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String date = cursor.getString(cursor.getColumnIndex("date"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));

        // get entry_row textviews
        TextView tv_title = view.findViewById(R.id.tv_titel2);
        TextView tv_date = view.findViewById(R.id.tv_datum2);
        TextView tv_mood = view.findViewById(R.id.tv_mood2);

        // set information to entry_row
        tv_title.setText(title);
        tv_date.setText(date);
        tv_mood.setText(mood);
    }

}
