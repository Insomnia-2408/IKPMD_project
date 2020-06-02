package com.hsleiden.ikpmd_project.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.hsleiden.ikpmd_project.Models.Route;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static SQLiteDatabase mSQLDB;
    private static DatabaseHelper mInstance;
    private static final String dbName = "cars.db";
    private static final int dbVersion = 1;		// Versie nr van je db.

    private DatabaseHelper(Context ctx) {
        super(ctx, dbName, null, dbVersion);	// gebruik de super constructor.
    }

    // synchronized … dit zorgt voor . . . . (?)
    // welk design pattern is dit ??  ==> Dit is een Singleton Design Pattern
    public static synchronized DatabaseHelper getHelper (Context ctx){
        if (mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            mSQLDB = mInstance.getWritableDatabase();
        }
        return mInstance;
    }

    @Override										// Maak je tabel met deze kolommen
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DatabaseInfo.RouteTable.ROUTETABLE + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseInfo.RouteColumn.START + " TEXT," +
                DatabaseInfo.RouteColumn.END + " TEXT," +
                DatabaseInfo.RouteColumn.STARTLATNG + " TEXT," +
                DatabaseInfo.RouteColumn.ENDLATNG + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseInfo.RouteTable.ROUTETABLE);
        onCreate(db);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ){
        super(context,name,factory, version);
    }

    public void insert(String table, String nullColumnHack, ContentValues values){
        mSQLDB.insert(table, nullColumnHack, values);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return mSQLDB.query(table, columns, selection, selectArgs, groupBy, having, orderBy);
    }

    public Route[] getRoutesFromDB() {

        List<Route> routes = new ArrayList<>();
        Route[] routesArray = {};
        Cursor cursor = this.query(DatabaseInfo.RouteTable.ROUTETABLE, new String[] {"*"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int index = 0;
            while (!cursor.isAfterLast()) {
                Route route = new Route();
                route.setStart(cursor.getString(cursor.getColumnIndex(DatabaseInfo.RouteColumn.START)));
                route.setEnd(cursor.getString(cursor.getColumnIndex(DatabaseInfo.RouteColumn.END)));
                routes.add(route);
                index ++;
                cursor.moveToNext();
            }
        }

        routesArray = routes.toArray(routesArray);
        return routesArray;
    }

}
