package ca.bcit.ass3.she_tan;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lulu on 2017-11-07.
 */

public class EventDetailActivity extends AppCompatActivity{
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        // get the event from the intent
        int eventId = getIntent().getIntExtra("event", 0);
        ListView listItems = (ListView)findViewById(R.id.list_items);

        String[] items = getItems(eventId);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, items
        );

        listItems.setAdapter(arrayAdapter);
    }

    private String[] getItems(int detailEventId) {
        SQLiteOpenHelper helper = new EventDbHelper(this);
        String[] items = null;
        try {
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT _detailId, NAME, UNIT, QUANTITY, detail_eventId FROM EVENT_DETAIL WHERE detail_eventId = " + detailEventId, null);

            int count = cursor.getCount();
            items = new String[count];
            if (cursor.moveToFirst()) {
                int ndx=0;
                do {
                    items[ndx++]=cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException sqlex) {
            String msg = "[MainActivity / getItems] DB unavailable";
            msg += "\n\n" + sqlex.toString();

            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
            //cursor.close();
        }finally {
        }
        return items;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (cursor != null)
            cursor.close();

        if (db != null)
            db.close();
    }


}
