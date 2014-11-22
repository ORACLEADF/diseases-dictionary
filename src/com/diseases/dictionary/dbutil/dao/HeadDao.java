package com.diseases.dictionary.dbutil.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import com.diseases.dictionary.R;
import com.diseases.dictionary.dbutil.DataBaseHelper;
import com.diseases.dictionary.dbutil.model.Head;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alireza on 11/22/14.
 */
public class HeadDao {
    private DataBaseHelper myDbHelper;
    private Context context;

    public HeadDao(Context ctx) {
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

    public List<Head> getAll() {
        String qry = context.getResources().getString(R.string.HeadDao_getAll);
        final Cursor c = myDbHelper.rawQuery(qry, null);
        List<Head> resultList = new ArrayList<Head>();

        while (c.moveToNext()) {
            Head head = new Head();
            head.setId(c.getLong(c.getColumnIndex("id")));
            head.setName(c.getString(c.getColumnIndex("name")));
            resultList.add(head);
        }
        c.close();

        return resultList;
    }
}
