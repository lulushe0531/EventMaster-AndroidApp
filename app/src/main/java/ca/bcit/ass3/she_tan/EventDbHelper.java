package ca.bcit.ass3.she_tan;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lulu on 2017-10-31.
 */

public class EventDbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "Event2.db";
    private static final int DB_VERSION = 2;
    private Context context;

    public EventDbHelper(Context context) {
        // The 3rd parameter (null) is an advanced feature relating to cursors
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDatabase(sqLiteDatabase, i, i1);
    }

    private String getCreateEventMasterTableSql() {
        String sql = "";
        sql += "CREATE TABLE EVENT_MASTER (";
        sql += "_eventId INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "DATE TEXT, ";
        sql += "TIME TEXT);";

        return sql;
    }

    private void insertEvent(SQLiteDatabase db, Event evt) {
        ContentValues values = new ContentValues();
        values.put("NAME", evt.getName());
        values.put("DATE", evt.getDate());
        values.put("TIME", evt.getTime());

        db.insert("EVENT_MASTER", null, values);
    }

    private static final Event[] myEvents = {
            new Event("Halloween Party", "Oct 30, 2017", "6:30 PM"),
            new Event("Christmas Party","December 20, 2017","12:30 PM"),
            new Event("New Year Eve","December 31, 2017","8:00 PM"),
    };

    private String getCreateEventDetailTableSql() {
        String sql = "";
        sql += "CREATE TABLE EVENT_DETAIL (";
        sql += "_detailId INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "NAME TEXT, ";
        sql += "UNIT TEXT, ";
        sql += "QUANTITY INTEGER, ";
        sql += "detail_eventId INTEGER, ";
        sql += "FOREIGN KEY (detail_eventId) REFERENCES EVENT_MASTER(_eventId));";

        return sql;
    }

    private void insertItem(SQLiteDatabase db, Item item) {
        ContentValues values = new ContentValues();
        values.put("NAME", item.get_name());
        values.put("UNIT", item.get_unit());
        values.put("QUANTITY", item.get_quantity());
        values.put("detail_eventId", 2);


        db.insert("EVENT_DETAIL",null,values);
    }

    private static final Item[] christItems = {
            new Item("Coca Cola", "6 packs", "5"),
            new Item("Pizza","Large","3"),
            new Item("Potato Chips","Large Bags","5"),
            new Item("Napkins","Pieces","100"),

    };


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL(getCreateEventMasterTableSql());
            for (Event e : myEvents) {
                insertEvent(db, e);
            }
        }

        if (oldVersion < 2) {
            db.execSQL(getCreateEventDetailTableSql());
            for (Item i : christItems) {
                insertItem(db, i);
            }
        }

    }
}
