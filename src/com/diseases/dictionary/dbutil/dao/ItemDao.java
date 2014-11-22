package com.diseases.dictionary.dbutil.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.diseases.dictionary.R;
import com.diseases.dictionary.dbutil.DataBaseHelper;
import com.diseases.dictionary.dbutil.model.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alireza on 11/22/14.
 */
public class ItemDao {
    private DataBaseHelper myDbHelper;
    private Context context;

    public ItemDao(Context ctx) {
        myDbHelper = new DataBaseHelper(ctx);
        this.context = ctx;
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }

    public List<Item> getAll(String row_id) {
        String qry = context.getResources().getString(R.string.ItemDao_getAll);
        String[] args = new String[1];
        args[0] = row_id;
        final Cursor c = myDbHelper.rawQuery(qry, args);
        List<Item> resultList = new ArrayList<Item>();

        while (c.moveToNext()) {
            Item Item = new Item();
            Item.setId(c.getLong(c.getColumnIndex("id")));
            Item.setComment(c.getString(c.getColumnIndex("comment")));
            resultList.add(Item);
        }
        c.close();

        return resultList;
    }
}
