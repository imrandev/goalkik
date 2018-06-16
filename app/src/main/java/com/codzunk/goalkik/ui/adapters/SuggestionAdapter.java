package com.codzunk.goalkik.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codzunk.goalkik.R;

public class SuggestionAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SearchView searchView;

    public SuggestionAdapter(Context context, Cursor cursor, SearchView sv) {
        super(context, cursor, false);
        mContext = context;
        searchView = sv;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mLayoutInflater.inflate(R.layout.simple_item_layout, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        String deal = cursor.getString(cursor.getColumnIndexOrThrow("suggestion"));

        TextView dealsTv = view.findViewById(R.id.item_suggestion);
        dealsTv.setText(deal);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}