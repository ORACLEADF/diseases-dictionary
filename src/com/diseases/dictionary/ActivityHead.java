package com.diseases.dictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diseases.dictionary.common.Statics;
import com.diseases.dictionary.dbutil.dao.HeadDao;
import com.diseases.dictionary.dbutil.model.Head;

import java.util.ArrayList;
import java.util.List;

public class ActivityHead extends Activity {
    LayoutInflater inflater;
    LinearLayout parent;
    List<Head> list = new ArrayList<Head>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        Statics statics = new Statics(getApplicationContext());

        ((TextView) findViewById(R.id.tv_header)).setTypeface(Statics.getFontTypeFace_Titr());
        ((TextView) findViewById(R.id.tv_footer)).setTypeface(Statics.getFontTypeFace_Titr());

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parent = (LinearLayout) findViewById(R.id.layout_content);

        HeadDao headDao = new HeadDao(getApplicationContext());
        list = headDao.getAll();
        if (list.size() == 0) {
            TextView v = new TextView(this);
            v.setText("رکوردی يافت نشد.");
            v.setTypeface(Statics.getFontTypeFace_Titr());
            v.setTextSize(30);
            v.setTextColor(Color.RED);
            parent.addView(v);
        } else {
            for (int i = 0; i < list.size(); i++) {
                View view = getNewView(i);
                parent.addView(view);
            }
        }
    }

    private View getNewView(int i) {
        View view = inflater.inflate(R.layout.row_head, null, false);
        Head head = list.get(i);

        if (head.getId() % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#FDE5B7"));
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFEB"));
        }

        TextView head_id = (TextView) view.findViewById(R.id.head_id);
        TextView head_name = (TextView) view.findViewById(R.id.head_name);

        head_id.setText(String.valueOf(head.getId()));
        head_id.setTypeface(Statics.getFontTypeFace_Zar());

        head_name.setText(head.getName());
        head_name.setTypeface(Statics.getFontTypeFace_Zar());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Statics.getColorHighLight());
                String row_id = ((TextView) view.findViewById(R.id.head_id)).getText().toString();
                String row_name = ((TextView) view.findViewById(R.id.head_name)).getText().toString();

                Intent i = new Intent();
                i.setClass(getApplicationContext(), ActivityItem.class);
                i.putExtra("row_id", row_id);
                i.putExtra("row_name", row_name);
                startActivity(i);
            }
        });

        return view;
    }
}