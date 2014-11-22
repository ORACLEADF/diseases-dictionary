package com.diseases.dictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.diseases.dictionary.common.Statics;
import com.diseases.dictionary.dbutil.dao.ItemDao;
import com.diseases.dictionary.dbutil.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alireza on 11/22/14.
 */
public class ActivityItem extends Activity {
    LayoutInflater inflater;
    LinearLayout parent;
    List<Item> list = new ArrayList<Item>();

    String row_id;
    String row_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent i = getIntent();
        row_id = i.getStringExtra("row_id");
        row_name = i.getStringExtra("row_name");

        ((TextView) findViewById(R.id.tv_header)).setTypeface(Statics.getFontTypeFace_Titr());
        ((TextView) findViewById(R.id.tv_header)).setText(row_name);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parent = (LinearLayout) findViewById(R.id.layout_content);

        ItemDao itemDao = new ItemDao(getApplicationContext());
        list = itemDao.getAll(row_id);
        if (list.size() == 0) {
            TextView v = new TextView(this);
            v.setText("رکوردی يافت نشد.");
            v.setTypeface(Statics.getFontTypeFace_Titr());
            v.setTextSize(30);
            v.setTextColor(Color.RED);
            parent.addView(v);
        } else {
            for (int j = 0; j < list.size(); j++) {
                View view = getNewView(j);
                parent.addView(view);
            }

            ImageView image = new ImageView(this);
            image.setMaxHeight(10);
            image.setMaxWidth(10);
            switch (Integer.valueOf(row_id)){
                case 1 : image.setImageResource(R.drawable.img_01); break;
                case 2 : image.setImageResource(R.drawable.img_02); break;
                case 3 : image.setImageResource(R.drawable.img_03); break;
                case 4 : image.setImageResource(R.drawable.img_04); break;
                case 5 : image.setImageResource(R.drawable.img_05); break;
                case 6 : image.setImageResource(R.drawable.img_06); break;
                case 7 : image.setImageResource(R.drawable.img_07); break;
                case 8 : image.setImageResource(R.drawable.img_08); break;
                case 9 : image.setImageResource(R.drawable.img_09); break;
                case 10 : image.setImageResource(R.drawable.img_10); break;
                case 11 : image.setImageResource(R.drawable.img_11); break;
                case 12 : image.setImageResource(R.drawable.img_12); break;
            }
            parent.addView(image);
        }
    }

    private View getNewView(int i) {
        View view = inflater.inflate(R.layout.row_item, null, false);
        Item head = list.get(i);

        if (head.getId() % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#CCE3FF"));
        } else {
            view.setBackgroundColor(Color.parseColor("#FFDFD3"));
        }

        TextView head_name = (TextView) view.findViewById(R.id.head_name);
        head_name.setText(head.getComment());
        head_name.setTypeface(Statics.getFontTypeFace_Zar());

        return view;
    }
}
