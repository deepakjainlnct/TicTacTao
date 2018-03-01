package com.tictactao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class GridAdapter extends BaseAdapter {
    private Context mContext;
    static final String[] symbols = new String[]{"", "", "", "", "", "", "", "", ""};
    private int selectedItem = -1;

    // Constructor
    public GridAdapter(Context context) {
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.game_grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.tv_move);
            textView.setText(symbols[position]);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }


    @Override
    public int getCount() {
        return symbols.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
