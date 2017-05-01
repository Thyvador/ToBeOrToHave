package com.polytech.unice.tobeortohave;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.polytech.unice.tobeortohave.dummy.ShopContent;
import com.polytech.unice.tobeortohave.list.shop.dummy.EmployeContent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by alexh on 16/04/2017.
 */

public class DbHandler extends SQLiteOpenHelper {
    private static final String DB_PATH = "/data/data/com.polytech.unice.tobeortohave/databases/";
    private static final String DB_NAME = "shops.db";
    private static DbHandler instance;

    private Context context;


    private DbHandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
//        try {
//            SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
//        } catch (SQLiteException e) {
            createDb();
//        }
    }


    public static DbHandler getInstance(Context context) {
        if (instance == null)
            instance = new DbHandler(context);
        return instance;
    }

    private void createDb() {
        try {
            SQLiteDatabase db = getWritableDatabase();
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("shops.sql")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0 && !line.contains("--")) {
                    while (!line.contains(";")) {
                        line += reader.readLine();
                    }
                    Log.d("Line ", line);
                    db.execSQL(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getShops() {
        if (ShopContent.ITEMS.isEmpty()) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM shops", null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ShopContent.addItem(new ShopContent.ShopDetail(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                ));
                cursor.moveToNext();
            }

            cursor.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void getEmployes(int shopId) {
        if (EmployeContent.ITEMS.isEmpty()) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM employes WHERE shopId=?", new String[]{String.valueOf(shopId)});

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                EmployeContent.addItem(new EmployeContent.EmployerDetails(
                        String.valueOf(cursor.getInt(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
                cursor.moveToNext();
            }

            cursor.close();
        }
    }
}
