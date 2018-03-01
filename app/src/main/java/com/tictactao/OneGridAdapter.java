package com.tictactao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class OneGridAdapter extends BaseAdapter {
    private Context mContext;
    static final String[] symbols = new String[]{"", "", "", "", "", "", "", "", ""};
    ArrayList<OnePlayerModel> list;
    String string;

    // Constructor
    public OneGridAdapter(Context context) {
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.game_grid_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.tv_move);
            textView.setText(symbols[position]);
            highlightItem(position, textView);
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


    private void highlightItem(int position, TextView result) {
        if (list != null && list.size() > 0) {
            for (OnePlayerModel onePlayerModel : list) {
                if (onePlayerModel.getPosition() == position) {
                    result.setText(onePlayerModel.getMove());
                }
            }
        }
    }

    public void setSelectedItem(ArrayList<OnePlayerModel> selectedItem) {
        this.list = selectedItem;
    }

}
